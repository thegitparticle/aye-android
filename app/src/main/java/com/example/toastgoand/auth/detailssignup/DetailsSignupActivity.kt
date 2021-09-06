package com.example.toastgoand.auth.detailssignup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.auth.otplogin.OtpLoginViewModel
import com.example.toastgoand.databinding.ActivityDetailsSignupBinding
import com.example.toastgoand.databinding.ActivityOtpLoginBinding

class DetailsSignupActivity : BaseActivity() {
    private lateinit var binding: ActivityDetailsSignupBinding

    private lateinit var viewModel: DetailsSignupViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = viewBinding as ActivityDetailsSignupBinding

        viewModel = ViewModelProvider(this).get(DetailsSignupViewModel::class.java)
        binding.detailsSignupModel = viewModel

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
        return ActivityDetailsSignupBinding.inflate(layoutInflater)
    }
}