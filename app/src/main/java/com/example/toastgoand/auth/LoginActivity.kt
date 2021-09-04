package com.example.toastgoand.auth

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.viewbinding.ViewBinding
import com.example.toastgoand.data.KEY_USER_LOGIN
//import com.example.toastgoand.data.impl.prefs.IPref
import com.example.toastgoand.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import com.example.toastgoand.databinding.LoginBinding
import com.example.toastgoand.home.LandingActivity
import com.example.toastgoand.navigator.Navigator
import com.example.toastgoand.navigator.Screen
import com.example.toastgoand.prefhelpers.Constant
import com.example.toastgoand.prefhelpers.PrefHelper
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity: BaseActivity() {

    private lateinit var binding: LoginBinding

    @Inject
    lateinit var navigator: Navigator

    lateinit var prefHelper: PrefHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = viewBinding as LoginBinding


        binding.button.setOnClickListener @Suppress("UNUSED_ANONYMOUS_PARAMETER") { view: View ->

            prefHelper = PrefHelper(this)
            prefHelper.put( Constant.PREF_IS_LOGIN, true)
            navigator.navigateTo(Screen.LANDING)
        }
    }

    override fun binding(): ViewBinding {
        return LoginBinding.inflate(layoutInflater)
    }

    override fun onBackPressed() {
        finishAffinity()
    }

}



