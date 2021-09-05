package com.example.toastgoand.auth

import android.content.Intent
import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.auth.welcome.WelcomeActivity
import com.example.toastgoand.databinding.LoginBinding
import com.example.toastgoand.navigator.Navigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
open class LoginActivity: BaseActivity() {

    open lateinit var binding: LoginBinding

    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = viewBinding as LoginBinding

        val intent = Intent(this, WelcomeActivity::class.java).apply {}
        startActivity(intent)
    }

    override fun binding(): ViewBinding {
        return LoginBinding.inflate(layoutInflater)
    }

    override fun onBackPressed() {
        finishAffinity()
    }

}



