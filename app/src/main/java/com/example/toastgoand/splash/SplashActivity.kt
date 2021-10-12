package com.example.toastgoand.splash

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.viewbinding.ViewBinding
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.R
import com.example.toastgoand.ToastgoApplication
import com.example.toastgoand.databinding.ActivitySplashBinding
import com.example.toastgoand.navigator.Navigator
import com.example.toastgoand.navigator.Screen
import com.example.toastgoand.network.AppRoomDB
import com.example.toastgoand.network.defaultrecos.DefaultRecosRepo
import com.example.toastgoand.network.directs.MyDirectsRepo
import com.example.toastgoand.network.myclans.MyClansRepo
import com.example.toastgoand.network.myfriends.MyFriendsRepo
import com.example.toastgoand.network.nudgelist.NudgeToRepo
import com.example.toastgoand.network.userdetails.UserDetailsRepo
import com.example.toastgoand.prefhelpers.Constant
import com.example.toastgoand.prefhelpers.PrefHelper
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
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

//    val applicationScope = CoroutineScope(SupervisorJob())
//    @ApplicationContext
//    var context: Context = applicationContext
//
////    val database by lazy { AppRoomDB.getDatabase(this.application as ToastgoApplication, applicationScope) }
//    val repository by lazy { UserDetailsRepo((this.application as ToastgoApplication).database.userDetailsDao()) }
//    val repositoryMyClans by lazy { MyClansRepo((this.application as ToastgoApplication).database.myClansDao()) }
//    val repositoryMyDirects by lazy { MyDirectsRepo((this.application as ToastgoApplication).database.myDirectsDao()) }
//    val repositoryNudgeTo by lazy { NudgeToRepo((this.application as ToastgoApplication).database.nudgeToDao()) }
//    val repositoryMyFriends by lazy { MyFriendsRepo((this.application as ToastgoApplication).database.myFriendsDao()) }
//    val repositoryDefaultRecos by lazy { DefaultRecosRepo((this.application as ToastgoApplication).database.defaultRecosDao()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("slowstartupdebug", "splash activity oncreate launched")
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
        Log.i("slowstartupdebug", "splash activity onresume in works")
        if (prefHelper.getBoolean( Constant.PREF_IS_LOGIN )) {
            navigator.navigateTo(Screen.LANDING)
            Log.i("slowstartupdebug", "splash activity ladning screen is navigated to")
        } else {
            navigator.navigateTo(Screen.LOGIN)
        }

    }

}
