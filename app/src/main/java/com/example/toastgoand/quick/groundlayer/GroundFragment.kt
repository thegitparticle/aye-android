package com.example.toastgoand.quick.groundlayer

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.toastgoand.R
import com.example.toastgoand.databinding.GroundFragmentBinding

class GroundFragment : Fragment() {

    private lateinit var binding: GroundFragmentBinding
    private lateinit var viewModel: GroundViewModel

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

        // Specify the fragment view as the lifecycle owner of the binding.
        // This is used so that the binding can observe LiveData updates
        binding.lifecycleOwner = viewLifecycleOwner

        binding.button3.setOnClickListener {
            findNavController().navigate(GroundFragmentDirections.actionGroundFragmentToCallFragment())
        }


        binding.button4.setOnClickListener {
//            findNavController().navigate(GroundFragmentDirections.actionGroundFragmentToMessageFragment())
            findNavController().navigate(GroundFragmentDirections.actionGroundFragmentToMultiProcess())

        }

        return binding.root
    }

}