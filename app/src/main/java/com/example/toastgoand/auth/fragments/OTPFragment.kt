package com.example.toastgoand.auth.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.toastgoand.R
import com.example.toastgoand.databinding.OtpFragmentBinding

class OTPFragment : Fragment() {

    private lateinit var viewModel: OTPViewModel
    private lateinit var binding: OtpFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.otp_fragment,
            container,
            false
        )

        viewModel = ViewModelProvider(this).get(OTPViewModel::class.java)

        // Specify the fragment view as the lifecycle owner of the binding.
        // This is used so that the binding can observe LiveData updates
        binding.lifecycleOwner = viewLifecycleOwner

        val args = OTPFragmentArgs.fromBundle(requireArguments())

        binding.otpFragmentHeader.text = args.phoneNumber

        return binding.root

    }

}