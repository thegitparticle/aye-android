package com.example.toastgoand.splash

import android.os.Bundle
import android.util.Log
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.viewbinding.ViewBinding
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.R
import com.example.toastgoand.databinding.ActivitySplashBinding
import com.example.toastgoand.navigator.Navigator
import com.example.toastgoand.navigator.Screen
import com.example.toastgoand.prefhelpers.Constant
import com.example.toastgoand.prefhelpers.PrefHelper
import dagger.hilt.android.AndroidEntryPoint
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

        Log.i("launchtimedebug", "splash activity on create called")
    }

    private fun bindViews() {
        binding = viewBinding as ActivitySplashBinding
//        splashLogoImage = binding.splashLogoImage
//        splashLogoImage.animation = AnimationUtils.loadAnimation(this, R.anim.fade_in)
    }

    override fun binding(): ViewBinding {
        return ActivitySplashBinding.inflate(layoutInflater)
    }

    override fun onResume() {
        super.onResume()

        Log.i("launchtimedebug", "splash activity on resume called")

        if (prefHelper.getBoolean( Constant.PREF_IS_LOGIN )) {
            Log.i("launchtimedebug", "splash activity on resume got pref value")
            navigator.navigateTo(Screen.LANDING)
        } else {
            Log.i("launchtimedebug", "splash activity on resume got pref value")
            navigator.navigateTo(Screen.LOGIN)
        }

    }

}
