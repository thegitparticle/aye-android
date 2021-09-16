package com.example.toastgoand.auth.loginsetup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.ToastgoApplication
import com.example.toastgoand.auth.clancreate.ClanCreatedViewModel
import com.example.toastgoand.auth.invitedby.InvitedByActivity
import com.example.toastgoand.auth.settingup.SettingUpViewModel
import com.example.toastgoand.auth.settingup.SettingUpViewModelFactory
import com.example.toastgoand.databinding.ActivityClanCreatedBinding
import com.example.toastgoand.databinding.ActivityLoginSetupBinding
import com.example.toastgoand.home.LandingActivity
import com.example.toastgoand.network.userdetails.UserDetailsDataClass
import com.example.toastgoand.prefhelpers.Constant
import com.example.toastgoand.prefhelpers.PrefHelper
import kotlinx.android.synthetic.main.start_call_dialog.*

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