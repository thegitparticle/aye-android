package com.example.toastgoand.auth.loginsetup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.viewbinding.ViewBinding
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.ToastgoApplication
import com.example.toastgoand.databinding.ActivityLoginSetupBinding
import com.example.toastgoand.home.LandingActivity
import com.example.toastgoand.prefhelpers.Constant
import com.example.toastgoand.prefhelpers.PrefHelper

class LoginSetupActivity : BaseActivity() {
    private lateinit var binding: ActivityLoginSetupBinding

    private val viewModel: LoginSetupViewModel by viewModels {
        LoginSetupViewModelFactory((application as ToastgoApplication).repository)
    }

    lateinit var prefHelper: PrefHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = viewBinding as ActivityLoginSetupBinding

        intent.getStringExtra("phoneNumber")?.let { viewModel.getUserDetailsWhileLoginHere(it) }

        viewModel.userDetails.observe(this, Observer { userDetailsGo ->
            binding.openHomeButton.visibility = View.VISIBLE
            Log.i("observer", "obseving is happening")
        })

        binding.openHomeButton.setOnClickListener {
            prefHelper = PrefHelper(this)
            prefHelper.put( Constant.PREF_IS_LOGIN, true)
            val intent = Intent(this, LandingActivity::class.java).apply {
                putExtra("phoneNumber", intent.getStringExtra("phoneNumber"))
            }
            startActivity(intent)
        }

    }

    override fun binding(): ViewBinding {
        return ActivityLoginSetupBinding.inflate(layoutInflater)
    }
}