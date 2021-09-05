package com.example.toastgoand.auth.fragments.enterphone

import android.content.res.AssetManager
import android.content.res.loader.AssetsProvider
import android.graphics.Typeface
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
import com.hbb20.CountryCodePicker

import androidx.core.content.res.ResourcesCompat
import com.example.toastgoand.auth.LoginActivity


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

        binding.lifecycleOwner = viewLifecycleOwner

        val typeface = context?.let { ResourcesCompat.getFont(it, R.font.lato) }

        val ccp = binding.ccp
        ccp.setTypeFace(typeface)

//        viewModel.userName.observe(viewLifecycleOwner, Observer<String> { newName ->
//            binding.userDetailsName.text = newName
//        })
//
//        binding.imageButton.setOnClickListener{
//            findNavController().navigate(EnterPhoneFragmentDirections.actionEnterPhoneFragmentToOTPFragment(
//                binding.editTextPhone.text.toString()
//            ))
//        }

        return binding.root
    }

}