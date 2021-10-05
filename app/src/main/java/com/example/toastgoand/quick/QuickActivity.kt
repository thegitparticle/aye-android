package com.example.toastgoand.quick

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.R
import com.example.toastgoand.auth.invitedby.InvitedByActivity
import com.example.toastgoand.databinding.ActivityLandingBinding
import com.example.toastgoand.databinding.ActivityQuickBinding
import com.example.toastgoand.databinding.GroundFragmentBinding
import com.example.toastgoand.quick.groundlayer.GroundFragmentDirections
import com.example.toastgoand.quick.groundlayer.GroundViewModel
import com.example.toastgoand.quick.network.apis.AgoraTokenApi
import com.google.common.util.concurrent.ListenableFuture
import kotlinx.android.synthetic.main.ground_fragment.*
import kotlinx.android.synthetic.main.start_call_dialog.*

class QuickActivity : BaseActivity() {

    private lateinit var binding: ActivityQuickBinding
    private lateinit var viewModel: QuickViewModel

    private lateinit var cameraProviderFuture: ListenableFuture<ProcessCameraProvider>

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)

        binding = viewBinding as ActivityQuickBinding

        viewModel = ViewModelProvider(this).get(QuickViewModel::class.java)
        binding.quickViewModel = viewModel

        val clubName = intent.getStringExtra("clubName").toString()
        val clubid = intent.getIntExtra("clubid", 0)
        val channelid = intent.getStringExtra("channelid").toString()
        val ongoingFrame = intent.getBooleanExtra("ongoingFrame", false)
        val startTime = intent.getStringExtra("startTime").toString()
        val endTime = intent.getStringExtra("endTime").toString()
        val userid = intent.getIntExtra("userid", 0)
        val directornot = intent.getBooleanExtra("directornot", false)

        viewModel.getTokenHere(userid = userid, channelid = channelid)

        cameraProviderFuture = ProcessCameraProvider.getInstance(this)

        fun bindPreview(cameraProvider: ProcessCameraProvider) {
            var preview: Preview = Preview.Builder()
                .build().also { it.setSurfaceProvider(previewView.surfaceProvider) }

            var cameraSelector: CameraSelector = CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_FRONT)
                .build()

            preview.setSurfaceProvider(previewView.surfaceProvider)

            var camera =
                cameraProvider.bindToLifecycle(this as LifecycleOwner, cameraSelector, preview)
        }

        cameraProviderFuture.addListener(Runnable {
            val cameraProvider = cameraProviderFuture.get()
            bindPreview(cameraProvider)
        }, ContextCompat.getMainExecutor(this))

        binding.cameraContainer.setOnClickListener {
            val intent = Intent(this, StreamCameraActivity::class.java).apply {
                putExtra("clubName", clubName)
                putExtra("clubid", clubid)
                putExtra("channelid", channelid)
                putExtra("ongoingFrame", ongoingFrame)
                putExtra("startTime", startTime)
                putExtra("endTime", endTime)
                putExtra("userid", userid)
                putExtra("directornot", directornot)
                putExtra("token", viewModel.token.value)
            }
            startActivity(intent)
        }

        binding.appStreamContainer.setOnClickListener {
            val intent = Intent(this, StreamMultiActivity::class.java).apply {
                putExtra("clubName", clubName)
                putExtra("clubid", clubid)
                putExtra("channelid", channelid)
                putExtra("ongoingFrame", ongoingFrame)
                putExtra("startTime", startTime)
                putExtra("endTime", endTime)
                putExtra("userid", userid)
                putExtra("directornot", directornot)
                putExtra("token", viewModel.token.value)
            }
            startActivity(intent)
        }

    }

    override fun binding(): ViewBinding {
        return ActivityQuickBinding.inflate(layoutInflater)
    }
}