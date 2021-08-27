package com.example.toastgoand.quick

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
import com.example.toastgoand.databinding.MessageFragmentBinding
import com.example.toastgoand.quick.groundlayer.GroundFragmentDirections
import com.example.toastgoand.quick.groundlayer.GroundViewModel

class MessageFragment : Fragment() {


    private lateinit var viewModel: MessageViewModel
    private lateinit var binding: MessageFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.message_fragment,
            container,
            false
        )

        viewModel = ViewModelProvider(this).get(MessageViewModel::class.java)
        binding.messageViewModel = viewModel

        // Specify the fragment view as the lifecycle owner of the binding.
        // This is used so that the binding can observe LiveData updates
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

}