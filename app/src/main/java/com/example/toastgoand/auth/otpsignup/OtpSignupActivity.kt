package com.example.toastgoand.auth.otpsignup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.auth.otplogin.OtpLoginViewModel
import com.example.toastgoand.databinding.ActivityOtpLoginBinding
import com.example.toastgoand.databinding.ActivityOtpSignupBinding

class OtpSignupActivity : BaseActivity() {
    private lateinit var binding: ActivityOtpSignupBinding

    private lateinit var viewModel: OtpSignupViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = viewBinding as ActivityOtpSignupBinding

        binding.firstPinView.requestFocus()

        viewModel = ViewModelProvider(this).get(OtpSignupViewModel::class.java)
        binding.otpSignupModel = viewModel

//        viewModel.phoneCheck.value?.let { it1 -> Log.i("EnterPhoneViewModelX", it1) }
//
//        viewModel.phoneCheck.observe(this, Observer { response ->
//            Log.i("EnterPhoneViewModelX", response)
//        })
//
//        binding.nextImageButton.setOnClickListener {
//            viewModel.getUserDetailsHere(countryCode + phoneNumber.toString())
//        }

    }

    override fun binding(): ViewBinding {
        return ActivityOtpSignupBinding.inflate(layoutInflater)
    }
}