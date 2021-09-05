package com.example.toastgoand.auth.welcome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.R
import com.example.toastgoand.auth.LoginActivity
import com.example.toastgoand.auth.enterphone.EnterPhoneActivity
import com.example.toastgoand.databinding.ActivityWelcomeBinding
import com.example.toastgoand.databinding.LoginBinding
import com.example.toastgoand.navigator.Navigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

class WelcomeActivity : BaseActivity() {

    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = viewBinding as ActivityWelcomeBinding

        binding.diveInButton.setOnClickListener {
            val intent = Intent(this, EnterPhoneActivity::class.java).apply {}
            startActivity(intent)
        }

    }

    override fun binding(): ViewBinding {
        return ActivityWelcomeBinding.inflate(layoutInflater)
    }
}