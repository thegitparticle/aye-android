package com.example.toastgoand.quick.callactivity

import android.content.pm.PackageManager
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.toastgoand.R
import com.example.toastgoand.databinding.CallFragmentBinding
import io.agora.rtc.IRtcEngineEventHandler
import io.agora.rtc.RtcEngine
import io.agora.rtc.video.VideoCanvas
import android.Manifest

class CallFragment : Fragment() {

    private lateinit var binding: CallFragmentBinding
    private lateinit var viewModel: CallViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.call_fragment,
            container,
            false
        )

        viewModel = ViewModelProvider(this).get(CallViewModel::class.java)
        binding.callViewModel = viewModel

        // Specify the fragment view as the lifecycle owner of the binding.
        // This is used so that the binding can observe LiveData updates
        binding.lifecycleOwner = viewLifecycleOwner

        // If all the permissions are granted, initialize the RtcEngine object and join a channel.
        if (checkSelfPermission(
                Manifest.permission.RECORD_AUDIO,
                PERMISSION_REQ_ID_RECORD_AUDIO
            ) && checkSelfPermission(Manifest.permission.CAMERA, PERMISSION_REQ_ID_CAMERA)
        ) {
            initializeAndJoinChannel()
        }

        return binding.root
    }

    private val PERMISSION_REQ_ID_RECORD_AUDIO = 22
    private val PERMISSION_REQ_ID_CAMERA = PERMISSION_REQ_ID_RECORD_AUDIO + 1

    private fun checkSelfPermission(permission: String, requestCode: Int): Boolean {
        if (ContextCompat.checkSelfPermission(requireActivity(), permission) !=
            PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(permission),
                requestCode
            )
            return false
        }
        return true
    }

    // Fill the App ID of your project generated on Agora Console.
    private val APP_ID = "5de5dd821ca244599496505f2481b447"

    // Fill the channel name.
    private val CHANNEL = "chan"

    // Fill the temp token generated on Agora Console.
    private val TOKEN = "0065de5dd821ca244599496505f2481b447IAD8WnxXDDZqLTE4PgArzfKU2Cq4mrg0no71yhWozMEJ0tAL/5gAAAAAEAA8nW45m8QsYQEAAQCaxCxh"

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
            mRtcEngine = RtcEngine.create(requireContext(), APP_ID, mRtcEventHandler)
        } catch (e: Exception) {

        }

        // By default, video is disabled, and you need to call enableVideo to start a video stream.
        mRtcEngine!!.enableVideo()

        val localContainer = (binding.localVideoViewContainer)as FrameLayout
        // Call CreateRendererView to create a SurfaceView object and add it as a child to the FrameLayout.
        val localFrame = RtcEngine.CreateRendererView(requireContext())
        localContainer.addView(localFrame)
        // Pass the SurfaceView object to Agora so that it renders the local video.
        mRtcEngine!!.setupLocalVideo(VideoCanvas(localFrame, VideoCanvas.RENDER_MODE_FIT, 0))

        // Join the channel with a token.
        mRtcEngine!!.joinChannel(TOKEN, CHANNEL, "", 0)
    }

    private fun setupRemoteVideo(uid: Int) {
        val remoteContainer = (binding.remoteVideoViewContainer) as FrameLayout

        val remoteFrame = RtcEngine.CreateRendererView(requireContext())
        remoteFrame.setZOrderMediaOverlay(true)
        remoteContainer.addView(remoteFrame)
        mRtcEngine!!.setupRemoteVideo(VideoCanvas(remoteFrame, VideoCanvas.RENDER_MODE_FIT, uid))
    }

    override fun onDestroy() {
        super.onDestroy()

        mRtcEngine?.leaveChannel()
        RtcEngine.destroy()
    }

}