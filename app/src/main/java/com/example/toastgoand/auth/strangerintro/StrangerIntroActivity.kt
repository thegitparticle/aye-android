package com.example.toastgoand.auth.strangerintro

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.R
import com.example.toastgoand.auth.clancreate.CreateClanActivity
import com.example.toastgoand.databinding.ActivityStrangerIntroBinding
import java.util.*
import kotlin.concurrent.schedule


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
            overridePendingTransition(
                R.anim.slide_from_right ,
                R.anim.slide_out_left
            )
        }

        binding.createClanButton.performClick()

        runOnUiThread(
            Timer("strangerIntroDelay", false).schedule(10000) {
                binding.createClanButton.performClick()
            }
        )

    }

    override fun binding(): ViewBinding {
        return ActivityStrangerIntroBinding.inflate(layoutInflater)
    }
}