package com.example.toastgoand.quick.groundlayer

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.GestureDetector
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import com.example.toastgoand.R
import com.example.toastgoand.databinding.GroundFragmentBinding
import com.google.common.util.concurrent.ListenableFuture
import kotlinx.android.synthetic.main.ground_fragment.*

class GroundFragment : Fragment() {

    private lateinit var binding: GroundFragmentBinding
    private lateinit var viewModel: GroundViewModel

    private lateinit var cameraProviderFuture: ListenableFuture<ProcessCameraProvider>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.ground_fragment,
            container,
            false
        )

        viewModel = ViewModelProvider(this).get(GroundViewModel::class.java)
        binding.groundViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())

        fun bindPreview(cameraProvider: ProcessCameraProvider) {
            var preview: Preview = Preview.Builder()
                .build().also { it.setSurfaceProvider(previewView.surfaceProvider) }

            var cameraSelector: CameraSelector = CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_FRONT)
                .build()

            preview.setSurfaceProvider(previewView.getSurfaceProvider())

            var camera =
                cameraProvider.bindToLifecycle(this as LifecycleOwner, cameraSelector, preview)
        }

        cameraProviderFuture.addListener(Runnable {
            val cameraProvider = cameraProviderFuture.get()
            bindPreview(cameraProvider)
        }, ContextCompat.getMainExecutor(requireContext()))

        binding.appStreamContainer.setOnClickListener{
            findNavController().navigate(GroundFragmentDirections.actionGroundFragmentToMultiProcess())
        }

        binding.cameraContainer.setOnClickListener {
            findNavController().navigate(GroundFragmentDirections.actionGroundFragmentToMultiProcess())
        }

//        binding.button3.setOnClickListener {
//            findNavController().navigate(GroundFragmentDirections.actionGroundFragmentToCallFragment())
//        }
//
//
//        binding.button4.setOnClickListener {
////            findNavController().navigate(GroundFragmentDirections.actionGroundFragmentToMessageFragment())
//            findNavController().navigate(GroundFragmentDirections.actionGroundFragmentToMultiProcess())
//
//        }

        return binding.root
    }

}