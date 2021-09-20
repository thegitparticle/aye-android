package com.example.toastgoand.home.clantalk.camera

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.viewbinding.ViewBinding
import com.example.toastgoand.BaseActivity
import android.Manifest
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.core.net.toFile
import androidx.core.net.toUri
import com.example.toastgoand.R
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.databinding.ActivityCameraBinding
import com.example.toastgoand.databinding.ActivityEnterPhoneBinding
import com.example.toastgoand.uibits.CircleIcon
import com.pubnub.api.PNConfiguration
import com.pubnub.api.PubNub
import compose.icons.FeatherIcons
import compose.icons.feathericons.Circle
import compose.icons.feathericons.File
import compose.icons.feathericons.RefreshCcw
import compose.icons.feathericons.X
import kotlinx.android.synthetic.main.activity_camera.*
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class CameraActivity : BaseActivity() {
    private var imageCapture: ImageCapture? = null

    private lateinit var binding: ActivityCameraBinding

    private lateinit var outputDirectory: File
    private lateinit var cameraExecutor: ExecutorService

    // Select back camera as a default
    var cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

    var savedUri: Uri = "".toUri()

    var clubName: String = ""
    var clubid: Int = 0
    var channelid : String = ""
    var ongoingFrame : Boolean = false
    var startTime : String = ""
    var endTime : String = ""
    var userid : String = ""
    var userdp : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = viewBinding as ActivityCameraBinding

        if (allPermissionsGranted()) {
            startCamera()
        } else {
            ActivityCompat.requestPermissions(
                this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS
            )
        }

        outputDirectory = this.getOutputDirectory()

        Log.i("CameraXBasic", "camera opened")

        val bottomButtons = binding.theBottomButtons

        bottomButtons.setContent {
            AyeTheme {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(100.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CircleIcon(iconName = FeatherIcons.File, modifier = Modifier)
                    Icon(
                        imageVector = FeatherIcons.Circle,
                        modifier = Modifier
                            .size(60.dp)
                            .clickable { takePhoto() },
                        contentDescription = "take pic icon"
                    )
                    Row(
                        modifier = Modifier
                            .size(40.dp)
                            .clickable {
                                cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA
                                Log.i("CameraXBasic", "clicked on cam change")
                            }
                    ) {
                        CircleIcon(
                            iconName = FeatherIcons.RefreshCcw,
                            modifier = Modifier.clickable {
                                cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA
                                Log.i("CameraXBasic", "clicked on cam change")
                            })
                    }
                }
            }
        }

        val sendButtons = binding.theSendButtons

        sendButtons.setContent {
            AyeTheme {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(100.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CircleIcon(iconName = FeatherIcons.X, modifier = Modifier.size(40.dp))
                    Box(
                        modifier = Modifier
                            .wrapContentWidth()
                            .clip(RoundedCornerShape(20.dp))
                            .background(MaterialTheme.colors.surface)
                            .clickable { sendCameraMessage() }
                            .width(75.dp)
                            .height(40.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "send",
                            style = MaterialTheme.typography.caption,
                            color = MaterialTheme.colors.onSurface
                        )
                    }
                }
            }
        }

        clubName = intent.getStringExtra("clubName").toString()
        clubid = intent.getIntExtra("clubid", 0)
        channelid = intent.getStringExtra("channelid").toString()
        ongoingFrame = intent.getBooleanExtra("ongoingFrame", false)
        startTime = intent.getStringExtra("startTime").toString()
        endTime = intent.getStringExtra("endTime").toString()
        userid = intent.getStringExtra("userid").toString()
        userdp = intent.getStringExtra("userdp").toString()
    }

    private fun sendCameraMessage() {
        val pnConfiguration = PNConfiguration().apply {
            subscribeKey = "sub-c-d099e214-9bcf-11eb-9adf-f2e9c1644994"
            publishKey = "pub-c-a65bb691-5b8a-4c4b-aef5-e2a26677122d"
            secure = true
            if (userid != null) {
                uuid = userid
            }
        }

        val pubnub = PubNub(pnConfiguration)

        data class metaHere (
            val type: String,
            val user_dp: String,
                )

        if (channelid != null) {
            pubnub.sendFile(
                channel = channelid,
                fileName = "galgalgal",
                inputStream =  savedUri.toFile().inputStream(),
                message = null,
                meta = userdp?.let { metaHere(type = "c", user_dp = it) },
                shouldStore = true
                ).async { result, status ->
                if (status.error) {
                    Log.i("CameraXBasic", status.error.toString())
                } else {
                    Log.i("CameraXBasic", "sending message worked")
                }
            }
        }

    }


    private fun takePhoto() {
        // Get a stable reference of the modifiable image capture use case
        val imageCapture = imageCapture ?: return

        Log.i("CameraXBasic", "clicked on takephone")

        // Create time-stamped output file to hold the image
        val photoFile = File(
            outputDirectory,
            SimpleDateFormat(
                FILENAME_FORMAT, Locale.US
            ).format(System.currentTimeMillis()) + ".jpg"
        )

        // Create output options object which contains file + metadata
        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()

        // Set up image capture listener, which is triggered after photo has
        // been taken
        imageCapture.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(this),
            object : ImageCapture.OnImageSavedCallback {
                override fun onError(exc: ImageCaptureException) {
                    Log.i("CameraXBasic Photo capture failed:", "${exc.message}")
                }

                override fun onImageSaved(output: ImageCapture.OutputFileResults) {
                    savedUri = Uri.fromFile(photoFile)
                    val msg = "CameraXBasic Photo capture succeeded: $savedUri"
                    binding.imageTaken.setImageURI(Uri.parse(savedUri.toString()))
                    binding.viewFinder.visibility = View.INVISIBLE
                    binding.theBottomButtons.visibility = View.INVISIBLE
                    binding.imageTaken.visibility = View.VISIBLE
                    binding.theSendButtons.visibility = View.VISIBLE
                    findViewById<ImageView>(R.id.imageTaken).setImageURI(savedUri)
                    viewFinder.visibility = View.INVISIBLE
                    Toast.makeText(baseContext, msg, Toast.LENGTH_SHORT).show()
                    Log.i("CameraXBasic Photo capture", msg)
                }
            })
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)

        cameraProviderFuture.addListener(Runnable {
            // Used to bind the lifecycle of cameras to the lifecycle owner
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            // Preview
            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(viewFinder.surfaceProvider)
                }

            //set Image Capture Builder
            imageCapture = ImageCapture.Builder()
                .setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
                .build()

            try {
                // Unbind use cases before rebinding
                cameraProvider.unbindAll()

                // Bind use cases to camera
                cameraProvider.bindToLifecycle(
                    this, cameraSelector, preview, imageCapture
                )

            } catch (exc: Exception) {
                Log.e(TAG, "Use case binding failed", exc)
            }

        }, ContextCompat.getMainExecutor(this))
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            baseContext, it
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun getOutputDirectory(): File {
        val mediaDir = externalMediaDirs.firstOrNull()?.let {
            File(it, resources.getString(R.string.app_name)).apply { mkdirs() }
        }
        return if (mediaDir != null && mediaDir.exists())
            mediaDir else filesDir
    }

    override fun onDestroy() {
        super.onDestroy()
//        cameraExecutor.shutdown()
    }

    companion object {
        private const val TAG = "CameraXBasic"
        private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
        private const val REQUEST_CODE_PERMISSIONS = 10
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
    }

    override fun binding(): ViewBinding {
        return ActivityCameraBinding.inflate(layoutInflater)
    }
}