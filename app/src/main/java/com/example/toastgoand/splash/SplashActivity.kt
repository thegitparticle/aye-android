package com.example.toastgoand.splash

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import androidx.viewbinding.ViewBinding
import com.example.toastgoand.data.KEY_USER_LOGIN
import dagger.hilt.android.AndroidEntryPoint
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.R
import com.example.toastgoand.auth.LoginActivity
import com.example.toastgoand.databinding.ActivitySplashBinding
import com.example.toastgoand.navigator.Screen
import com.example.toastgoand.navigator.Navigator
import com.example.toastgoand.data.DELAY_HOME
import com.example.toastgoand.data.DELAY_LOGIN
import com.example.toastgoand.home.LandingActivity
import com.example.toastgoand.prefhelpers.Constant
import com.example.toastgoand.prefhelpers.PrefHelper
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : BaseActivity() {

    // view objects
    lateinit var binding: ActivitySplashBinding
    lateinit var splashLogoImage: ImageView
    lateinit var prefHelper: PrefHelper

    // dependency objects
    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindViews()
        prefHelper = PrefHelper(this)
    }

    private fun bindViews() {
        binding = viewBinding as ActivitySplashBinding
        splashLogoImage = binding.splashLogoImage
        splashLogoImage.animation = AnimationUtils.loadAnimation(this, R.anim.fade_in)
    }

    override fun binding(): ViewBinding {
        return ActivitySplashBinding.inflate(layoutInflater)
    }

    override fun onResume() {
        super.onResume()

        if (prefHelper.getBoolean( Constant.PREF_IS_LOGIN )) {
            navigator.navigateTo(Screen.LANDING)
        } else {
            navigator.navigateTo(Screen.LOGIN)
        }

    }

}
