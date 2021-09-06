package com.example.toastgoand.auth.strangerintro

import android.R.id
import android.app.PendingIntent.getActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.auth.clancreate.CreateClanActivity
import com.example.toastgoand.auth.invitedby.InvitedByViewModel
import com.example.toastgoand.auth.otpsignup.OtpSignupActivity
import com.example.toastgoand.databinding.ActivityInvitedByBinding
import com.example.toastgoand.databinding.ActivityStrangerIntroBinding
import java.util.*
import kotlin.concurrent.schedule
import android.R.id.button1
import android.os.Handler


class StrangerIntroActivity : BaseActivity() {
    private lateinit var binding: ActivityStrangerIntroBinding

    private lateinit var viewModel: StrangerIntroViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = viewBinding as ActivityStrangerIntroBinding

        viewModel = ViewModelProvider(this).get(StrangerIntroViewModel::class.java)

        binding.createClanButton.setOnClickListener {
            val intent = Intent(this, CreateClanActivity::class.java).apply {
            }
            startActivity(intent)
        }

        binding.createClanButton.performClick()

//        runOnUiThread(
//            Timer("strangerIntroDelay", false).schedule(10000) {
//                binding.createClanButton.performClick()
//            }
//        )

    }

    override fun binding(): ViewBinding {
        return ActivityStrangerIntroBinding.inflate(layoutInflater)
    }
}