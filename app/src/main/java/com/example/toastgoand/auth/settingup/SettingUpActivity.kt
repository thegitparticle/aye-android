package com.example.toastgoand.auth.settingup

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.viewbinding.ViewBinding
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.ToastgoApplication
import com.example.toastgoand.auth.invitedby.InvitedByActivity
import com.example.toastgoand.databinding.ActivitySettingUpBinding

class SettingUpActivity : BaseActivity() {
    private lateinit var binding: ActivitySettingUpBinding

    private val viewModel: SettingUpViewModel by viewModels {
        SettingUpViewModelFactory((application as ToastgoApplication).repository)
    }


    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                Log.i("Permission: ", "Granted")
            } else {
                Log.i("Permission: ", "Denied")
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = viewBinding as ActivitySettingUpBinding

        requestPermissionLauncher.launch(
            Manifest.permission.READ_CONTACTS
        )

        intent.getStringExtra("phoneNumber")?.let { viewModel.getUserDetailsHere(it) }

        binding.allowContactsButton.setOnClickListener {
            val intent = Intent(this, InvitedByActivity::class.java).apply {
                putExtra("phoneNumber", intent.getStringExtra("phoneNumber"))
            }
            startActivity(intent)
        }

        viewModel.userDetails.observe(this) {
            deets -> binding.username.text = deets.user.full_name
        }

    }

    override fun binding(): ViewBinding {
        return ActivitySettingUpBinding.inflate(layoutInflater)
    }

}