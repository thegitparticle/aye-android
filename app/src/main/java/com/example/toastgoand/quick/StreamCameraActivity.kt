package com.example.toastgoand.quick

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.FrameLayout
import androidx.activity.compose.BackHandler
import androidx.camera.core.CameraSelector
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.databinding.ActivityQuickBinding
import com.example.toastgoand.databinding.ActivityStreamCameraBinding
import com.example.toastgoand.home.LandingActivity
import com.example.toastgoand.home.clantalk.ClanTalkActivity
import com.example.toastgoand.uibits.CircleIcon
import compose.icons.FeatherIcons
import compose.icons.feathericons.*
import io.agora.rtc.IRtcEngineEventHandler
import io.agora.rtc.RtcEngine
import io.agora.rtc.video.VideoCanvas

class StreamCameraActivity : BaseActivity() {

    private lateinit var binding: ActivityStreamCameraBinding
    private lateinit var viewModel: StreamCameraViewModel

    var clubName: String = ""
    var clubid: Int = 0
    var channelid : String = ""
    var ongoingFrame : Boolean = false
    var startTime : String = ""
    var endTime : String = ""
    var userid : Int = 0
    var directornot : Boolean = false
    var token: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = viewBinding as ActivityStreamCameraBinding

        viewModel = ViewModelProvider(this).get(StreamCameraViewModel::class.java)

        clubName = intent.getStringExtra("clubName").toString()
        clubid = intent.getIntExtra("clubid", 0)
        channelid = intent.getStringExtra("channelid").toString()
        ongoingFrame = intent.getBooleanExtra("ongoingFrame", false)
        startTime = intent.getStringExtra("startTime").toString()
        endTime = intent.getStringExtra("endTime").toString()
        userid = intent.getIntExtra("userid", 0)
        directornot = intent.getBooleanExtra("directornot", false)
        token = intent.getStringExtra("token").toString()

        // If all the permissions are granted, initialize the RtcEngine object and join a channel.
        if (checkSelfPermission(
                Manifest.permission.RECORD_AUDIO,
                PERMISSION_REQ_ID_RECORD_AUDIO
            ) && checkSelfPermission(Manifest.permission.CAMERA, PERMISSION_REQ_ID_CAMERA)
        ) {
            initializeAndJoinChannel()
        }

        Log.i("streamworking", channelid)

    }

    private val PERMISSION_REQ_ID_RECORD_AUDIO = 22
    private val PERMISSION_REQ_ID_CAMERA = PERMISSION_REQ_ID_RECORD_AUDIO + 1

    private fun checkSelfPermission(permission: String, requestCode: Int): Boolean {
        if (ContextCompat.checkSelfPermission(this, permission) !=
            PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(permission),
                requestCode
            )
            return false
        }
        return true

    }

    // Fill the App ID of your project generated on Agora Console.
    private val APP_ID = "851193d91b1945bda153a38f3584ead3"

    // Fill the channel name.
//    private val CHANNEL = "90_c"

    // Fill the temp token generated on Agora Console.
//    private val TOKEN = "006851193d91b1945bda153a38f3584ead3IAC0sdltLv7UMVotFrpkgxQLknllea32mQpUuLOpCS2Eut78lGEAAAAAEADy5cWP7NZdYQEAAQDr1l1h"

    private var mRtcEngine: RtcEngine? = null

    private val mRtcEventHandler = object : IRtcEngineEventHandler() {
        // Listen for the remote user joining the channel to get the uid of the user.
        override fun onUserJoined(uid: Int, elapsed: Int) {
//            runOnUiThread {
            // Call setupRemoteVideo to set the remote video view after getting uid from the onUserJoined callback.
            setupRemoteVideo(uid)
//            }
        }
    }

    private fun initializeAndJoinChannel() {
        try {
            mRtcEngine = RtcEngine.create(this, APP_ID, mRtcEventHandler)
        } catch (e: Exception) {

        }

        // By default, video is disabled, and you need to call enableVideo to start a video stream.
        mRtcEngine!!.enableVideo()

        val localContainer = (binding.localVideoViewContainer) as FrameLayout
        // Call CreateRendererView to create a SurfaceView object and add it as a child to the FrameLayout.
        val localFrame = RtcEngine.CreateRendererView(this)
        localContainer.addView(localFrame)
        // Pass the SurfaceView object to Agora so that it renders the local video.
        mRtcEngine!!.setupLocalVideo(VideoCanvas(localFrame, VideoCanvas.RENDER_MODE_FIT, 0))

        // Join the channel with a token.
        mRtcEngine!!.joinChannel(token, channelid, "", userid)

        val composeView = binding.composeView

        composeView.setContent {
            AyeTheme() {

                var muted by remember { mutableStateOf(false) }
                var openDialog = remember { mutableStateOf(false) }

                BackHandler(enabled = true, onBack = {
                    openDialog.value = true
                })

                val context = LocalContext.current

                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(100.dp)
                        .background(MaterialTheme.colors.primary),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier
                            .size(40.dp)
                            .clickable {
                                mRtcEngine?.switchCamera()
                                Log.i("streamworking", "clicked on cam change")
                            }
                    ) {
                        CircleIcon(iconName = FeatherIcons.Repeat, modifier = Modifier)
                    }
                    Icon(
                        imageVector = FeatherIcons.XCircle,
                        modifier = Modifier
                            .size(60.dp)
                            .clickable {
                                mRtcEngine?.leaveChannel()
                                val intent = Intent(context, LandingActivity::class.java).apply {}
                                startActivity(intent)
                            },
                        contentDescription = "take pic icon"
                    )
                    Row(
                        modifier = Modifier
                            .size(40.dp)
                            .clickable {
                                Log.i("streamworking", "clicked on cam change")
                                mRtcEngine?.muteLocalAudioStream(!muted)
                                muted = !muted
                            }
                    ) {
                        CircleIcon(
                            iconName = FeatherIcons.Mic,
                            modifier = Modifier.clickable {
                                Log.i("streamworking", "clicked on cam change")
                            })
                    }
                }

                Column {
                    Button(onClick = {
                        openDialog.value = true
                    }) {
                        Text("Click me")
                    }

                    if (openDialog.value) {

                        AlertDialog(
                            onDismissRequest = {
                                // Dismiss the dialog when the user clicks outside the dialog or on the back
                                // button. If you want to disable that functionality, simply use an empty
                                // onCloseRequest.
                                openDialog.value = false
                            },
                            title = {
                                Text(text = "Dialog Title")
                            },
                            text = {
                                Text("Here is a text ")
                            },
                            confirmButton = {
                                Button(

                                    onClick = {
                                        openDialog.value = false
                                    }) {
                                    Text("This is the Confirm Button")
                                }
                            },
                            dismissButton = {
                                Button(

                                    onClick = {
                                        openDialog.value = false
                                    }) {
                                    Text("This is the dismiss Button")
                                }
                            }
                        )
                    }
                }
            }
        }
    }

    private fun setupRemoteVideo(uid: Int) {
        val remoteContainer = (binding.remoteVideoViewContainer) as FrameLayout

        val remoteFrame = RtcEngine.CreateRendererView(this)
        remoteFrame.setZOrderMediaOverlay(true)
        remoteContainer.addView(remoteFrame)
        mRtcEngine!!.setupRemoteVideo(VideoCanvas(remoteFrame, VideoCanvas.RENDER_MODE_FIT, uid))
    }

    override fun onDestroy() {
        super.onDestroy()

        mRtcEngine?.leaveChannel()
        RtcEngine.destroy()
    }

    override fun binding(): ViewBinding {
        return ActivityStreamCameraBinding.inflate(layoutInflater)
    }
}