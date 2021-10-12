package com.example.toastgoand.auth.welcome

import android.content.Intent
import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.R
import com.example.toastgoand.auth.enterphone.EnterPhoneActivity
import com.example.toastgoand.databinding.ActivityWelcomeBinding

class WelcomeActivity : BaseActivity() {

    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = viewBinding as ActivityWelcomeBinding

        binding.diveInButton.setOnClickListener {
            val intent = Intent(this, EnterPhoneActivity::class.java).apply {}
            startActivity(intent)
            overridePendingTransition(
                R.anim.slide_from_right ,
                R.anim.slide_out_left
            )
        }

        binding.animationView.setAnimation(R.raw.loading_ping_pong_cup)

    }

    override fun binding(): ViewBinding {
        return ActivityWelcomeBinding.inflate(layoutInflater)
    }
}