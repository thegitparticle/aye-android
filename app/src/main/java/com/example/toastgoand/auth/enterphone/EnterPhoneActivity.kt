package com.example.toastgoand.auth.enterphone

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.auth.detailssignup.DetailsSignupActivity
import com.example.toastgoand.auth.otplogin.OtpLoginActivity
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
//            viewModel.getUserDetailsHere(countryCode + phoneNumber.toString())
            val intent = Intent(this, DetailsSignupActivity::class.java).apply{
                putExtra("phoneNumber", countryCode + phoneNumber.toString())
            }
            startActivity(intent)
        }

    }

    override fun binding(): ViewBinding {
        return ActivityEnterPhoneBinding.inflate(layoutInflater)
    }

}