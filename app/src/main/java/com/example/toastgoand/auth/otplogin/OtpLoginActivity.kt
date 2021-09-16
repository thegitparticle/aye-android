package com.example.toastgoand.auth.otplogin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.auth.enterphone.EnterPhoneViewModel
import com.example.toastgoand.auth.loginsetup.LoginSetupActivity
import com.example.toastgoand.auth.otpsignup.OtpSignupActivity
import com.example.toastgoand.databinding.ActivityEnterPhoneBinding
import com.example.toastgoand.databinding.ActivityOtpLoginBinding

class OtpLoginActivity : BaseActivity() {
    private lateinit var binding: ActivityOtpLoginBinding

    private lateinit var viewModel: OtpLoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = viewBinding as ActivityOtpLoginBinding

        binding.firstPinView.requestFocus()

        viewModel = ViewModelProvider(this).get(OtpLoginViewModel::class.java)
        binding.otpLoginModel = viewModel

//        viewModel.phoneCheck.value?.let { it1 -> Log.i("EnterPhoneViewModelX", it1) }
//
//        viewModel.phoneCheck.observe(this, Observer { response ->
//            Log.i("EnterPhoneViewModelX", response)
//        })
//
        binding.nextImageButton.setOnClickListener {
//            intent.getStringExtra("phoneNumber")?.let { it1 -> viewModel.getUserDetailsHere(it1) }
            val intent = Intent(this, LoginSetupActivity::class.java).apply {
                putExtra("phoneNumber", intent.getStringExtra("phoneNumber"))
            }
            startActivity(intent)
        }

    }

    override fun binding(): ViewBinding {
        return ActivityOtpLoginBinding.inflate(layoutInflater)
    }
}