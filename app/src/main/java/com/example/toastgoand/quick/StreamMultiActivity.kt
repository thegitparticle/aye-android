package com.example.toastgoand.quick

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import android.util.DisplayMetrics
import android.util.Log
import android.view.SurfaceView
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import androidx.activity.compose.BackHandler
import androidx.appcompat.widget.AppCompatButton
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.R
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.databinding.ActivityStreamCameraBinding
import com.example.toastgoand.databinding.ActivityStreamMultiBinding
import com.example.toastgoand.home.LandingActivity
import com.example.toastgoand.quick.callactivity.ScreenSharingClient
import com.example.toastgoand.quick.callactivity.ScreenSharingClient.IStateListener
import com.example.toastgoand.quick.callactivity.utils.CommonUtil
import com.example.toastgoand.uibits.CircleIcon
import com.example.toastgoand.uibits.CircleIconCustomColorSize
import com.yanzhenjie.permission.AndPermission
import com.yanzhenjie.permission.runtime.Permission
import compose.icons.FeatherIcons
import compose.icons.feathericons.*
import io.agora.rtc.Constants
import io.agora.rtc.IRtcEngineEventHandler
import io.agora.rtc.IRtcEngineEventHandler.RtcStats
import io.agora.rtc.RtcEngine
import io.agora.rtc.models.ChannelMediaOptions
import io.agora.rtc.video.VideoCanvas
import io.agora.rtc.video.VideoEncoderConfiguration
import io.agora.rtc.video.VideoEncoderConfiguration.*
import kotlinx.android.synthetic.main.activity_stream_multi.*
import java.lang.Exception

class StreamMultiActivity : BaseActivity() {

    private lateinit var binding: ActivityStreamMultiBinding
    private lateinit var viewModel: StreamMultiViewModel

    var clubName: String = ""
    var clubid: Int = 0
    var channelid: String = ""
    var ongoingFrame: Boolean = false
    var startTime: String = ""
    var endTime: String = ""
    var userid: Int = 0
    var directornot: Boolean = false
    var token: String = ""
    var token222: String = ""

    //    private val TAG = MultiProcess::class.java.simpleName
    private val SCREEN_SHARE_UID = 10000
// replace these where needed with binding layout values

    private var fl_local: FrameLayout? = null
    private var fl_remote: FrameLayout? = null
    private var join: Button? = null
    private var screenShare: Button? = null
    private var et_channel: EditText? = null
    private var engine: RtcEngine? = null
    private var myUid = userid
    private var joined = false
    private var isSharing = false
    private var mSSClient: ScreenSharingClient? = null

    protected var handler: Handler? = null

    private val mListener: IStateListener = object : IStateListener {
        override fun onError(error: Int) {
            Log.i("streammultigoon", "Screen share service error happened: $error")
        }

        override fun onTokenWillExpire() {
            Log.i("streammultigoon", "Screen share service token will expire")
            mSSClient!!.renewToken(null) // Replace the token with your valid token
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = viewBinding as ActivityStreamMultiBinding

        viewModel = ViewModelProvider(this).get(StreamMultiViewModel::class.java)


        clubName = intent.getStringExtra("clubName").toString()
        clubid = intent.getIntExtra("clubid", 0)
        channelid = intent.getStringExtra("channelid").toString()
        ongoingFrame = intent.getBooleanExtra("ongoingFrame", false)
        startTime = intent.getStringExtra("startTime").toString()
        endTime = intent.getStringExtra("endTime").toString()
        userid = intent.getIntExtra("userid", 0)
        directornot = intent.getBooleanExtra("directornot", false)
        token222 = intent.getStringExtra("token").toString()
        token =
            "006851193d91b1945bda153a38f3584ead3IACKJYOK3qEVqWrM8B8cbCFYFshFp1np5QrT0GWvMUjCeN78lGEAAAAAEADy5cWPzaFeYQEAAQDMoV5h"

        Log.i("streammultigoon userid", userid.toString())
        Log.i("streammultigoon token", token222)

        join = binding.btnJoin
        (join as AppCompatButton).setOnClickListener { this }

        screenShare = binding.screenShare
        (screenShare as AppCompatButton).setEnabled(false)
        (screenShare as AppCompatButton).setOnClickListener { this }

        et_channel = binding.etChannel

        fl_local = binding.flLocal
        fl_remote = binding.flRemote

        handler = Handler(Looper.getMainLooper())

        val composeView = binding.composeView

        composeView.setContent {
            AyeTheme() {

                var muted by remember { mutableStateOf(false) }
                var cameraOff by remember { mutableStateOf(false) }
                var openDialog = remember { mutableStateOf(false) }

                BackHandler(enabled = true, onBack = {
                    openDialog.value = true
                })

                val context = LocalContext.current

                Row(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(100.dp)
                        .background(AyeTheme.colors.uiBackground.copy(0.0f)),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier
                            .size(75.dp)
                            .clickable {
                                Log.i("camerastream", "clicked on cam change")
                                engine?.muteLocalVideoStream(!cameraOff)
                                cameraOff = !cameraOff
                            },
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CircleIconCustomColorSize(
                            iconName = if (cameraOff) {
                                FeatherIcons.CameraOff
                            } else {
                                FeatherIcons.Camera
                            },
                            modifier = Modifier,
                            color = Color.White, colorBg = Color.Black, size = 35.dp
                        )
                    }
                    Icon(
                        FeatherIcons.XCircle,
                        "stop stream",
                        tint = AyeTheme.colors.error,
                        modifier = Modifier
                            .size(60.dp)
                            .clickable {
                                engine?.leaveChannel()
//                                mSSClient!!.stop(context)
                                screenShare!!.text = resources.getString(R.string.screenshare)
                                isSharing = false
                                val intent = Intent(context, LandingActivity::class.java).apply {}
                                startActivity(intent)
                            },
                    )
                    Row(
                        modifier = Modifier
                            .size(75.dp)
                            .clickable {
                                Log.i("camerastream", "clicked on cam change")
                                engine?.muteLocalAudioStream(!muted)
                                muted = !muted
                            },
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        CircleIconCustomColorSize(
                            iconName = if (muted) {
                                FeatherIcons.MicOff
                            } else {
                                FeatherIcons.Mic
                            },
                            modifier = Modifier,
                            color = Color.White, colorBg = Color.Black, size = 35.dp
                        )
                    }
                }

                Column {
//                    Button(onClick = {
//                        openDialog.value = true
//                    }) {
//                        Text("Click me")
//                    }

                    if (openDialog.value) {

                        AlertDialog(
                            onDismissRequest = {
                                // Dismiss the dialog when the user clicks outside the dialog or on the back
                                // button. If you want to disable that functionality, simply use an empty
                                // onCloseRequest.
                                openDialog.value = false
                            },
                            title = {
                                Text(text = "stop stream?")
                            },
                            text = {
                                Text("your friends get to view it in messages")
                            },
                            confirmButton = {
                                Box(
                                    modifier = Modifier
                                        .wrapContentWidth()
                                        .clip(RoundedCornerShape(20.dp))
                                        .background(AyeTheme.colors.error)
                                        .clickable {
                                            openDialog.value = false
                                            engine?.leaveChannel()
//                                mSSClient!!.stop(context)
                                            screenShare!!.text = resources.getString(R.string.screenshare)
                                            isSharing = false
                                            val intent = Intent(context, LandingActivity::class.java).apply {}
                                            startActivity(intent)
                                        }
                                        .width(100.dp)
                                        .height(40.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "stop",
                                        style = MaterialTheme.typography.subtitle2,
                                        color = AyeTheme.colors.uiBackground
                                    )
                                }
                            },
                            dismissButton = {
                                Box(
                                    modifier = Modifier
                                        .wrapContentWidth()
                                        .clip(RoundedCornerShape(20.dp))
                                        .background(AyeTheme.colors.uiSurface)
                                        .clickable {
                                            openDialog.value = false
                                        }
                                        .width(100.dp)
                                        .height(40.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "no.. no..",
                                        style = MaterialTheme.typography.subtitle2,
                                        color = AyeTheme.colors.textSecondary
                                    )
                                }
                            },
                            backgroundColor = AyeTheme.colors.uiBackground,
                            contentColor = AyeTheme.colors.textSecondary
                        )
                    }
                }
            }
        }

        try {
            /**Creates an RtcEngine instance.
             * @param context The context of Android Activity
             * @param appId The App ID issued to you by Agora. See [
 * How to get the App ID](https://docs.agora.io/en/Agora%20Platform/token#get-an-app-id)
             * @param handler IRtcEngineEventHandler is an abstract class providing default implementation.
             * The SDK uses this class to report to the app on SDK runtime events.
             */
            engine = RtcEngine.create(
                this,
                "851193d91b1945bda153a38f3584ead3",
                iRtcEngineEventHandler
            )

            // Initialize Screen Share Client
            mSSClient = ScreenSharingClient.getInstance()
            mSSClient?.setListener(mListener)

            if (AndPermission.hasPermissions(
                    this,
                    Permission.Group.STORAGE,
                    Permission.Group.MICROPHONE,
                    Permission.Group.CAMERA
                )
            ) {
                Log.i("streammultigoon if permission", "join channels called")
                joinChannel(channelid)
                if (!isSharing) {
                    mSSClient!!.start(
                        this, "851193d91b1945bda153a38f3584ead3", token,
                        channelid, userid, VideoEncoderConfiguration(
                            getScreenDimensions(),
                            FRAME_RATE.FRAME_RATE_FPS_30,
                            VideoEncoderConfiguration.STANDARD_BITRATE,
                            ORIENTATION_MODE.ORIENTATION_MODE_ADAPTIVE
                        )
                    )
                    screenShare!!.text = resources.getString(R.string.stop)
                    isSharing = true
                } else {
                    mSSClient!!.stop(this)
                    screenShare!!.text = resources.getString(R.string.screenshare)
                    isSharing = false
                }
                return
            }
            // Request permission
            AndPermission.with(this).runtime().permission(
                Permission.Group.STORAGE,
                Permission.Group.MICROPHONE,
                Permission.Group.CAMERA
            ).onGranted { permissions: List<String?>? ->
                // Permissions Granted
                Log.i("streammultigoon asked for permission", "join channels called")
                joinChannel(channelid)
                if (!isSharing) {
                    mSSClient!!.start(
                        this, "851193d91b1945bda153a38f3584ead3", token,
                        channelid, userid, VideoEncoderConfiguration(
                            getScreenDimensions(),
                            FRAME_RATE.FRAME_RATE_FPS_30,
                            VideoEncoderConfiguration.STANDARD_BITRATE,
                            ORIENTATION_MODE.ORIENTATION_MODE_ADAPTIVE
                        )
                    )
                    screenShare!!.text = resources.getString(R.string.stop)
                    isSharing = true
                } else {
                    mSSClient!!.stop(this)
                    screenShare!!.text = resources.getString(R.string.screenshare)
                    isSharing = false
                }
            }.start()

        } catch (e: Exception) {
            e.printStackTrace()
            this.onBackPressed()
        }

    }

//    fun onClick(v: View) {
//        if (v.id == R.id.btn_join) {
//            if (!joined) {
//                CommonUtil.hideInputBoard(this, et_channel)
//                // call when join button hit
//                val channelId = et_channel!!.text.toString()
//                // Check permission
//                if (AndPermission.hasPermissions(
//                        this,
//                        Permission.Group.STORAGE,
//                        Permission.Group.MICROPHONE,
//                        Permission.Group.CAMERA
//                    )
//                ) {
//                    joinChannel(channelId)
//                    return
//                }
//                // Request permission
//                AndPermission.with(this).runtime().permission(
//                    Permission.Group.STORAGE,
//                    Permission.Group.MICROPHONE,
//                    Permission.Group.CAMERA
//                ).onGranted { permissions: List<String?>? ->
//                    // Permissions Granted
//                    joinChannel(channelId)
//                }.start()
//            } else {
//                joined = false
//                /**After joining a channel, the user must call the leaveChannel method to end the
//                 * call before joining another channel. This method returns 0 if the user leaves the
//                 * channel and releases all resources related to the call. This method call is
//                 * asynchronous, and the user has not exited the channel when the method call returns.
//                 * Once the user leaves the channel, the SDK triggers the onLeaveChannel callback.
//                 * A successful leaveChannel method call triggers the following callbacks:
//                 * 1:The local client: onLeaveChannel.
//                 * 2:The remote client: onUserOffline, if the user leaving the channel is in the
//                 * Communication channel, or is a BROADCASTER in the Live Broadcast profile.
//                 * @returns 0: Success.
//                 * < 0: Failure.
//                 * PS:
//                 * 1:If you call the destroy method immediately after calling the leaveChannel
//                 * method, the leaveChannel process interrupts, and the SDK does not trigger
//                 * the onLeaveChannel callback.
//                 * 2:If you call the leaveChannel method during CDN live streaming, the SDK
//                 * triggers the removeInjectStreamUrl method.
//                 */
//                engine!!.leaveChannel()
//                join!!.text = getString(R.string.join)
//                mSSClient!!.stop(this)
//                screenShare!!.text = resources.getString(R.string.screenshare)
//                screenShare!!.isEnabled = false
//                isSharing = false
//            }
//        } else if (v.id == R.id.screenShare) {
//            val channelId = et_channel!!.text.toString()
//            if (!isSharing) {
//                mSSClient!!.start(
//                    this, "851193d91b1945bda153a38f3584ead3", token,
//                    channelid, userid, VideoEncoderConfiguration(
//                        getScreenDimensions(),
//                        FRAME_RATE.FRAME_RATE_FPS_30,
//                        VideoEncoderConfiguration.STANDARD_BITRATE,
//                        ORIENTATION_MODE.ORIENTATION_MODE_ADAPTIVE
//                    )
//                )
//                screenShare!!.text = resources.getString(R.string.stop)
//                isSharing = true
//            } else {
//                mSSClient!!.stop(this)
//                screenShare!!.text = resources.getString(R.string.screenshare)
//                isSharing = false
//            }
//        }
//    }

    private fun getScreenDimensions(): VideoDimensions? {
        val manager = this.getSystemService(WINDOW_SERVICE) as WindowManager
        val outMetrics = DisplayMetrics()
        manager.defaultDisplay.getMetrics(outMetrics)
        return VideoDimensions(outMetrics.widthPixels / 2, outMetrics.heightPixels / 2)
    }

    private fun joinChannel(channelId: String) {
        // Check if the context is valid
        val context: Context = this ?: return

        // Create render view by RtcEngine
        val surfaceView = RtcEngine.CreateRendererView(context)
        if (fl_local!!.childCount > 0) {
            fl_local!!.removeAllViews()
        }
        // Add to the local container
        fl_local!!.addView(
            surfaceView,
            FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
        )
        // Setup local video to render your local camera preview
        engine!!.setupLocalVideo(VideoCanvas(surfaceView, VideoCanvas.RENDER_MODE_HIDDEN, 0))
        // Set audio route to microPhone
        engine!!.disableAudio()
        //        engine.setDefaultAudioRoutetoSpeakerphone(false);
        /** Sets the channel profile of the Agora RtcEngine.
         * CHANNEL_PROFILE_COMMUNICATION(0): (Default) The Communication profile.
         * Use this profile in one-on-one calls or group calls, where all users can talk freely.
         * CHANNEL_PROFILE_LIVE_BROADCASTING(1): The Live-Broadcast profile. Users in a live-broadcast
         * channel have a role as either broadcaster or audience. A broadcaster can both send and receive streams;
         * an audience can only receive streams. */
        engine!!.setChannelProfile(Constants.CHANNEL_PROFILE_LIVE_BROADCASTING)
        /**In the demo, the default is to enter as the anchor. */
        engine!!.setClientRole(IRtcEngineEventHandler.ClientRole.CLIENT_ROLE_BROADCASTER)
        // Enable video module
        engine!!.enableVideo()
        // Setup video encoding configs
        val configuration = VideoEncoderConfiguration(
            VD_640x360, FRAME_RATE.FRAME_RATE_FPS_30,
            STANDARD_BITRATE,
            ORIENTATION_MODE.ORIENTATION_MODE_FIXED_PORTRAIT
        )
        engine!!.setVideoEncoderConfiguration(configuration)
        //        engine.setVideoEncoderConfiguration(new VideoEncoderConfiguration( VideoEncoderConfiguration.FRAME_RATE.valueOf(VD_640x360),
//                FRAME_RATE_FPS_15,
//                STANDARD_BITRATE,
//                ORIENTATION_MODE_ADAPTIVE
//        ));

//        engine.setVideoEncoderConfiguration(new VideoEncoderConfiguration(
//                ((TheApplication)getActivity().getApplication()).getGlobalSettings().getVideoEncodingDimensionObject(),
//                VideoEncoderConfiguration.FRAME_RATE.valueOf(((TheApplication)getActivity().getApplication()).getGlobalSettings().getVideoEncodingFrameRate()),
//                STANDARD_BITRATE,
//                VideoEncoderConfiguration.ORIENTATION_MODE.valueOf(((TheApplication)getActivity().getApplication()).getGlobalSettings().getVideoEncodingOrientation())
//        ));
        /**Please configure accessToken in the string_config file.
         * A temporary token generated in Console. A temporary token is valid for 24 hours. For details, see
         * https://docs.agora.io/en/Agora%20Platform/token?platform=All%20Platforms#get-a-temporary-token
         * A token generated at the server. This applies to scenarios with high-security requirements. For details, see
         * https://docs.agora.io/en/cloud-recording/token_server_java?platform=Java */
        var accessToken: String? = token
        if (TextUtils.equals(accessToken, "") || TextUtils.equals(
                accessToken,
                "<#YOUR ACCESS TOKEN#>"
            )
        ) {
            accessToken = null
        }
        /** Allows a user to join a channel.
         * if you do not specify the uid, we will generate the uid for you */
        val option = ChannelMediaOptions()
        option.autoSubscribeAudio = false
        option.autoSubscribeVideo = false
        val res =
            engine!!.joinChannel(accessToken, channelId, "Extra Optional Data", userid, option)
        if (res != 0) {
            // Usually happens with invalid parameters
            // Error code description can be found at:
            // en: https://docs.agora.io/en/Voice/API%20Reference/java/classio_1_1agora_1_1rtc_1_1_i_rtc_engine_event_handler_1_1_error_code.html
            // cn: https://docs.agora.io/cn/Voice/API%20Reference/java/classio_1_1agora_1_1rtc_1_1_i_rtc_engine_event_handler_1_1_error_code.html
//            showAlert(RtcEngine.getErrorDescription(Math.abs(res)))
            return
        }
        // Prevent repeated entry
        join!!.isEnabled = false

        if (directornot) {
            viewModel.startStreamClubServerCalls(
                userid = userid,
                channelid = channelid,
                clubid = clubid
            )
        }
    }

    private val iRtcEngineEventHandler: IRtcEngineEventHandler = object : IRtcEngineEventHandler() {
        /**Reports a warning during SDK runtime.
         * Warning code: https://docs.agora.io/en/Voice/API%20Reference/java/classio_1_1agora_1_1rtc_1_1_i_rtc_engine_event_handler_1_1_warn_code.html */
        override fun onWarning(warn: Int) {
            Log.w(
                "streammultigoon iRtcEngineEventHandler onWarning",
                String.format(
                    "onWarning code %d message %s",
                    warn,
                    RtcEngine.getErrorDescription(warn)
                )
            )
        }

        /**Reports an error during SDK runtime.
         * Error code: https://docs.agora.io/en/Voice/API%20Reference/java/classio_1_1agora_1_1rtc_1_1_i_rtc_engine_event_handler_1_1_error_code.html */
        override fun onError(err: Int) {
            Log.e(
                "streammultigoon iRtcEngineEventHandler onError",
                String.format("onError code %d message %s", err, RtcEngine.getErrorDescription(err))
            )
//            showAlert(
//                String.format(
//                    "onError code %d message %s",
//                    err,
//                    RtcEngine.getErrorDescription(err)
//                )
//            )
        }

        /**Occurs when a user leaves the channel.
         * @param stats With this callback, the application retrieves the channel information,
         * such as the call duration and statistics.
         */
        override fun onLeaveChannel(stats: RtcStats) {
            super.onLeaveChannel(stats)
            Log.i(
                "streammultigoon iRtcEngineEventHandler onleave",
                String.format("local user %d leaveChannel!", userid)
            )
            if (directornot) {
                viewModel.stopStreamClubServerCalls(userid = userid, clubid = clubid, channelid = channelid)
            }
//            showLongToast(String.format("local user %d leaveChannel!", myUid))
        }

        /**Occurs when the local user joins a specified channel.
         * The channel name assignment is based on channelName specified in the joinChannel method.
         * If the uid is not specified when joinChannel is called, the server automatically assigns a uid.
         * @param channel Channel name
         * @param uid User ID
         * @param elapsed Time elapsed (ms) from the user calling joinChannel until this callback is triggered
         */
        override fun onJoinChannelSuccess(channel: String, userid: Int, elapsed: Int) {
            Log.i(
                "streammultigoon iRtcEngineEventHandler sucess",
                String.format("onJoinChannelSuccess channel %s uid %d", channel, userid)
            )
//            showLongToast(String.format("onJoinChannelSuccess channel %s uid %d", channel, uid))
            myUid = userid
            joined = true
            handler?.post(Runnable {
                join!!.isEnabled = true
                join!!.text = getString(R.string.leave)
                screenShare!!.isEnabled = true
            })
        }

        /**Since v2.9.0.
         * This callback indicates the state change of the remote audio stream.
         * PS: This callback does not work properly when the number of users (in the Communication profile) or
         * broadcasters (in the Live-broadcast profile) in the channel exceeds 17.
         * @param uid ID of the user whose audio state changes.
         * @param state State of the remote audio
         * REMOTE_AUDIO_STATE_STOPPED(0): The remote audio is in the default state, probably due
         * to REMOTE_AUDIO_REASON_LOCAL_MUTED(3), REMOTE_AUDIO_REASON_REMOTE_MUTED(5),
         * or REMOTE_AUDIO_REASON_REMOTE_OFFLINE(7).
         * REMOTE_AUDIO_STATE_STARTING(1): The first remote audio packet is received.
         * REMOTE_AUDIO_STATE_DECODING(2): The remote audio stream is decoded and plays normally,
         * probably due to REMOTE_AUDIO_REASON_NETWORK_RECOVERY(2),
         * REMOTE_AUDIO_REASON_LOCAL_UNMUTED(4) or REMOTE_AUDIO_REASON_REMOTE_UNMUTED(6).
         * REMOTE_AUDIO_STATE_FROZEN(3): The remote audio is frozen, probably due to
         * REMOTE_AUDIO_REASON_NETWORK_CONGESTION(1).
         * REMOTE_AUDIO_STATE_FAILED(4): The remote audio fails to start, probably due to
         * REMOTE_AUDIO_REASON_INTERNAL(0).
         * @param reason The reason of the remote audio state change.
         * REMOTE_AUDIO_REASON_INTERNAL(0): Internal reasons.
         * REMOTE_AUDIO_REASON_NETWORK_CONGESTION(1): Network congestion.
         * REMOTE_AUDIO_REASON_NETWORK_RECOVERY(2): Network recovery.
         * REMOTE_AUDIO_REASON_LOCAL_MUTED(3): The local user stops receiving the remote audio
         * stream or disables the audio module.
         * REMOTE_AUDIO_REASON_LOCAL_UNMUTED(4): The local user resumes receiving the remote audio
         * stream or enables the audio module.
         * REMOTE_AUDIO_REASON_REMOTE_MUTED(5): The remote user stops sending the audio stream or
         * disables the audio module.
         * REMOTE_AUDIO_REASON_REMOTE_UNMUTED(6): The remote user resumes sending the audio stream
         * or enables the audio module.
         * REMOTE_AUDIO_REASON_REMOTE_OFFLINE(7): The remote user leaves the channel.
         * @param elapsed Time elapsed (ms) from the local user calling the joinChannel method
         * until the SDK triggers this callback.
         */
        override fun onRemoteAudioStateChanged(userid: Int, state: Int, reason: Int, elapsed: Int) {
            super.onRemoteAudioStateChanged(userid, state, reason, elapsed)
            Log.i(
                "streammultigoon iRtcEngineEventHandler audio change",
                "onRemoteAudioStateChanged->$userid, state->$state, reason->$reason"
            )
        }

        /**Since v2.9.0.
         * Occurs when the remote video state changes.
         * PS: This callback does not work properly when the number of users (in the Communication
         * profile) or broadcasters (in the Live-broadcast profile) in the channel exceeds 17.
         * @param uid ID of the remote user whose video state changes.
         * @param state State of the remote video:
         * REMOTE_VIDEO_STATE_STOPPED(0): The remote video is in the default state, probably due
         * to REMOTE_VIDEO_STATE_REASON_LOCAL_MUTED(3), REMOTE_VIDEO_STATE_REASON_REMOTE_MUTED(5),
         * or REMOTE_VIDEO_STATE_REASON_REMOTE_OFFLINE(7).
         * REMOTE_VIDEO_STATE_STARTING(1): The first remote video packet is received.
         * REMOTE_VIDEO_STATE_DECODING(2): The remote video stream is decoded and plays normally,
         * probably due to REMOTE_VIDEO_STATE_REASON_NETWORK_RECOVERY (2),
         * REMOTE_VIDEO_STATE_REASON_LOCAL_UNMUTED(4), REMOTE_VIDEO_STATE_REASON_REMOTE_UNMUTED(6),
         * or REMOTE_VIDEO_STATE_REASON_AUDIO_FALLBACK_RECOVERY(9).
         * REMOTE_VIDEO_STATE_FROZEN(3): The remote video is frozen, probably due to
         * REMOTE_VIDEO_STATE_REASON_NETWORK_CONGESTION(1) or REMOTE_VIDEO_STATE_REASON_AUDIO_FALLBACK(8).
         * REMOTE_VIDEO_STATE_FAILED(4): The remote video fails to start, probably due to
         * REMOTE_VIDEO_STATE_REASON_INTERNAL(0).
         * @param reason The reason of the remote video state change:
         * REMOTE_VIDEO_STATE_REASON_INTERNAL(0): Internal reasons.
         * REMOTE_VIDEO_STATE_REASON_NETWORK_CONGESTION(1): Network congestion.
         * REMOTE_VIDEO_STATE_REASON_NETWORK_RECOVERY(2): Network recovery.
         * REMOTE_VIDEO_STATE_REASON_LOCAL_MUTED(3): The local user stops receiving the remote
         * video stream or disables the video module.
         * REMOTE_VIDEO_STATE_REASON_LOCAL_UNMUTED(4): The local user resumes receiving the remote
         * video stream or enables the video module.
         * REMOTE_VIDEO_STATE_REASON_REMOTE_MUTED(5): The remote user stops sending the video
         * stream or disables the video module.
         * REMOTE_VIDEO_STATE_REASON_REMOTE_UNMUTED(6): The remote user resumes sending the video
         * stream or enables the video module.
         * REMOTE_VIDEO_STATE_REASON_REMOTE_OFFLINE(7): The remote user leaves the channel.
         * REMOTE_VIDEO_STATE_REASON_AUDIO_FALLBACK(8): The remote media stream falls back to the
         * audio-only stream due to poor network conditions.
         * REMOTE_VIDEO_STATE_REASON_AUDIO_FALLBACK_RECOVERY(9): The remote media stream switches
         * back to the video stream after the network conditions improve.
         * @param elapsed Time elapsed (ms) from the local user calling the joinChannel method until
         * the SDK triggers this callback.
         */
        override fun onRemoteVideoStateChanged(userid: Int, state: Int, reason: Int, elapsed: Int) {
            super.onRemoteVideoStateChanged(userid, state, reason, elapsed)
            Log.i(
                "streammultigoon iRtcEngineEventHandler video change",
                "onRemoteVideoStateChanged->$userid, state->$state, reason->$reason"
            )
        }

        /**Occurs when a remote user (Communication)/host (Live Broadcast) joins the channel.
         * @param uid ID of the user whose audio state changes.
         * @param elapsed Time delay (ms) from the local user calling joinChannel/setClientRole
         * until this callback is triggered.
         */
        override fun onUserJoined(userid: Int, elapsed: Int) {
            super.onUserJoined(userid, elapsed)
            Log.i("streammultigoon iRtcEngineEventHandler userjoin", "onUserJoined->$userid")
//            showLongToast(String.format("user %d joined!", uid))
            // don't render screen sharing view
            if (userid == userid) {
                return
            }
            /**Check if the context is correct */
            /**Check if the context is correct */
            val context: Context = (this ?: return) as Context
            handler?.post(Runnable {
                /**Display remote video stream */
                /**Display remote video stream */
                var surfaceView: SurfaceView? = null
                if (fl_remote!!.childCount > 0) {
                    fl_remote!!.removeAllViews()
                }
                // Create render view by RtcEngine
                surfaceView = RtcEngine.CreateRendererView(context)
                surfaceView.setZOrderMediaOverlay(true)
                // Add to the remote container
                fl_remote!!.addView(
                    surfaceView,
                    FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                )

                // Setup remote video to render
                engine!!.setupRemoteVideo(
                    VideoCanvas(
                        surfaceView,
                        VideoCanvas.RENDER_MODE_HIDDEN,
                        userid
                    )
                )
            })
        }

        /**Occurs when a remote user (Communication)/host (Live Broadcast) leaves the channel.
         * @param uid ID of the user whose audio state changes.
         * @param reason Reason why the user goes offline:
         * USER_OFFLINE_QUIT(0): The user left the current channel.
         * USER_OFFLINE_DROPPED(1): The SDK timed out and the user dropped offline because no data
         * packet was received within a certain period of time. If a user quits the
         * call and the message is not passed to the SDK (due to an unreliable channel),
         * the SDK assumes the user dropped offline.
         * USER_OFFLINE_BECOME_AUDIENCE(2): (Live broadcast only.) The client role switched from
         * the host to the audience.
         */
        override fun onUserOffline(userid: Int, reason: Int) {
            Log.i(
                "streammultigoon iRtcEngineEventHandler",
                String.format("user %d offline! reason:%d", userid, reason)
            )
//            showLongToast(String.format("user %d offline! reason:%d", uid, reason))
            if (userid == userid) {
                return
            }
            handler?.post(Runnable {
                /**Clear render view
                 * Note: The video will stay at its last frame, to completely remove it you will need to
                 * remove the SurfaceView from its parent */
                /**Clear render view
                 * Note: The video will stay at its last frame, to completely remove it you will need to
                 * remove the SurfaceView from its parent */
                /**Clear render view
                 * Note: The video will stay at its last frame, to completely remove it you will need to
                 * remove the SurfaceView from its parent */

                /**Clear render view
                 * Note: The video will stay at its last frame, to completely remove it you will need to
                 * remove the SurfaceView from its parent */
                engine!!.setupRemoteVideo(VideoCanvas(null, VideoCanvas.RENDER_MODE_HIDDEN, userid))
            })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        /**leaveChannel and Destroy the RtcEngine instance */
        if (engine != null) {
            engine!!.leaveChannel()
        }
        if (isSharing) {
            mSSClient!!.stop(this)
        }
        handler?.post(Runnable { RtcEngine.destroy() })
        engine = null
    }

    override fun binding(): ViewBinding {
        return ActivityStreamMultiBinding.inflate(layoutInflater)
    }
}