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
import com.google.common.util.concurrent.ListenableFuture
import kotlinx.android.synthetic.main.ground_fragment.*

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
//        binding.lifecycleOwner = viewLifecycleOwner

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

        binding.appStreamContainer.setOnClickListener {

//            findNavController().navigate(GroundFragmentDirections.actionGroundFragmentToMultiProcess())
        }

        binding.cameraContainer.setOnClickListener {
            val intent = Intent(this, StreamCameraActivity::class.java).apply {
//                putExtra("phoneNumber", intent.getStringExtra("phoneNumber"))
            }
            startActivity(intent)
        }

    }

    override fun binding(): ViewBinding {
        return ActivityQuickBinding.inflate(layoutInflater)
    }
}