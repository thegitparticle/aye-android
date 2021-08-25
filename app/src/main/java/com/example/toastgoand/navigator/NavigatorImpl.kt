package com.example.toastgoand.navigator

import android.content.Intent
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.FragmentActivity
import com.example.toastgoand.auth.LoginActivity
import com.example.toastgoand.home.HomeActivity
import com.example.toastgoand.resrc.IRes
import javax.inject.Inject

class NavigatorImpl @Inject constructor(
    private val activity: FragmentActivity,
//    val res: @JvmSuppressWildcards(true) IRes
): Navigator {

    override fun navigateTo(screen: Screen) {
        when (screen) {
            Screen.LOGIN -> {
                activity.startActivity(Intent(activity, LoginActivity::class.java))
            }
            Screen.HOME -> {
                activity.startActivity(Intent(activity, HomeActivity::class.java))
            }
        }
    }


//    private fun activityOptions(view: View): ActivityOptionsCompat {
//        return ActivityOptionsCompat.makeSceneTransitionAnimation(
//            activity,
//            view,
//        )
//    }

}