package com.example.toastgoand.auth.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.toastgoand.R
import com.example.toastgoand.databinding.EnterPhoneBinding

class EnterPhoneFragment: Fragment() {

    private lateinit var binding: EnterPhoneBinding
    private lateinit var viewModel: EnterPhoneViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.enter_phone,
            container,
            false
        )

        Log.i("EnterPhoneFragment", "View model provider.get() has been called")

        viewModel = ViewModelProvider(this).get(EnterPhoneViewModel::class.java)
        binding.enterPhoneViewModel = viewModel

        // Specify the fragment view as the lifecycle owner of the binding.
        // This is used so that the binding can observe LiveData updates
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.userName.observe(viewLifecycleOwner, Observer<String> { newName ->
            binding.userDetailsName.text = newName
        })

        binding.imageButton.setOnClickListener{
            findNavController().navigate(EnterPhoneFragmentDirections.actionEnterPhoneFragmentToOTPFragment(
                binding.editTextPhone.text.toString()
            ))
        }

        return binding.root
    }

}