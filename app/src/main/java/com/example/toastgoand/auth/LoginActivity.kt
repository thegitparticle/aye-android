package com.example.toastgoand.auth

import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.SimpleAdapter
import androidx.viewbinding.ViewBinding
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.R
import com.example.toastgoand.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity: BaseActivity() {

    lateinit var binding: ActivityLoginBinding
    lateinit var loginLogoImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindViews()
    }

    private fun bindViews() {
        binding = viewBinding as ActivityLoginBinding
        loginLogoImage = binding.loginLogoImage
    }

    override fun binding(): ViewBinding {
        return ActivityLoginBinding.inflate(layoutInflater)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }

}

