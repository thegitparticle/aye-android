package com.example.toastgoand.splash

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import androidx.viewbinding.ViewBinding
import dagger.hilt.android.AndroidEntryPoint
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.R
import com.example.toastgoand.databinding.ActivitySplashBinding
import com.example.toastgoand.navigator.Screen
import com.example.toastgoand.resrc.DELAY_HOME
import com.example.toastgoand.navigator.Navigator
import com.example.toastgoand.resrc.DELAY_LOGIN
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity: BaseActivity() {

    // view objects
    lateinit var binding: ActivitySplashBinding
    lateinit var splashLogoImage: ImageView

    // dependency objects
    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindViews()
    }

    private fun bindViews() {
        binding = viewBinding as ActivitySplashBinding
        splashLogoImage = binding.splashLogoImage
        splashLogoImage.animation = AnimationUtils.loadAnimation(this, R.anim.fade_in)
    }

    override fun binding(): ViewBinding {
        return ActivitySplashBinding.inflate(layoutInflater)
    }

        override fun onResume () {
        super.onResume()

        splashLogoImage.animate().setListener(object: AnimatorListenerAdapter() {
            fun onAnimatedEnd(animation: Animator?) {
                loadNextScreen(
                    Screen.LOGIN,
                DELAY_LOGIN)
            }
        }).start()
    }

    private fun loadNextScreen(screen: Screen, delay: Long) {
        Handler(Looper.getMainLooper()).postDelayed({
            navigator.navigateTo(screen, splashLogoImage, "logoImageTransition")
        }, delay)
    }
}

//    override fun onResume () {
//        super.onResume()

//        logoImage.animate().setListener(object: Animator.AnimatorListenerAdapter() {
//            override fun onAnimatedEnd(animation: Animator?) {
//                loadNextScreen(if pref.bool(KEY_USER_LOGIN)) Screen.HOME else Screen.LOGIN,
//                if (pref.bool(KEY_USER_LOGIN)) DELAY_HOME else DELAY_LOGIN)
//            }
//        }).start()
//    }

//    private fun loadNextScreen(screen: Screen, delay: long) {
//        Handler(Looper.getMainLooper()).postDelayed({
//            navigator.navigateTo(screen, logoImage, res.str("logoImageTransition"))
//        }, delay)
//    }