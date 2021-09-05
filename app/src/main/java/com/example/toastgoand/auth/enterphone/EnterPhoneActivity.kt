package com.example.toastgoand.auth.enterphone

import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.databinding.ActivityEnterPhoneBinding
import com.hbb20.CountryCodePicker.OnCountryChangeListener


class EnterPhoneActivity : BaseActivity() {

    private lateinit var binding: ActivityEnterPhoneBinding

    private lateinit var viewModel: EnterPhoneViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = viewBinding as ActivityEnterPhoneBinding

        binding.editTextPhone.requestFocus()

        val phoneNumber: Editable? = binding.editTextPhone.getText()

        var countryCode: String = binding.ccp.selectedCountryCodeWithPlus

        binding.ccp.setOnCountryChangeListener(OnCountryChangeListener {
            countryCode = binding.ccp.selectedCountryCodeWithPlus
        })

        viewModel = ViewModelProvider(this).get(EnterPhoneViewModel::class.java)
        binding.enterPhoneModel = viewModel

        viewModel.phoneCheck.value?.let { it1 -> Log.i("EnterPhoneViewModelX", it1) }

        viewModel.phoneCheck.observe(this, Observer { response ->
            Log.i("EnterPhoneViewModelX", response)
        })

        binding.nextImageButton.setOnClickListener {
            Log.i("EnterPhoneActivity", phoneNumber.toString())
            Log.i("EnterPhoneActivity", countryCode + phoneNumber.toString())
            viewModel.getUserDetailsHere(countryCode + phoneNumber.toString())
        }

    }

    override fun binding(): ViewBinding {
        return ActivityEnterPhoneBinding.inflate(layoutInflater)
    }

}