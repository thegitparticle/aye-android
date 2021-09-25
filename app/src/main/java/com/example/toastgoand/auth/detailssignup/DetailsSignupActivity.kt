package com.example.toastgoand.auth.detailssignup

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.auth.otpsignup.OtpSignupActivity
import com.example.toastgoand.databinding.ActivityDetailsSignupBinding

class DetailsSignupActivity : BaseActivity() {
    private lateinit var binding: ActivityDetailsSignupBinding

    private lateinit var viewModel: DetailsSignupViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = viewBinding as ActivityDetailsSignupBinding

        viewModel = ViewModelProvider(this).get(DetailsSignupViewModel::class.java)
        binding.detailsSignupModel = viewModel

        binding.nextImageButton.setOnClickListener {
            val intent = Intent(this, OtpSignupActivity::class.java).apply {
                putExtra("phoneNumber", intent.getStringExtra("phoneNumber"))
            }
            startActivity(intent)
        }

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