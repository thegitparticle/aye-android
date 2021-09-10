package com.example.toastgoand.auth.settingup

import android.content.Intent
import android.media.MediaRecorder.VideoSource.CAMERA
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.auth.detailssignup.DetailsSignupActivity
import com.example.toastgoand.auth.enterphone.EnterPhoneViewModel
import com.example.toastgoand.databinding.ActivityEnterPhoneBinding
import com.example.toastgoand.databinding.ActivitySettingUpBinding
import android.Manifest
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.room.Room
import com.example.toastgoand.ToastgoApplication
import com.example.toastgoand.auth.invitedby.InvitedByActivity
import com.example.toastgoand.auth.otpsignup.OtpSignupActivity

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

        viewModel.userDetailsHere.observe(this, {details -> details?.image?.let {
            Log.i("roomdbdata",
                it
            )
        } })

        viewModel.myUserName.observe(this, Observer { newUserName ->
            binding.username.text = newUserName
        })

        binding.allowContactsButton.setOnClickListener {
            val intent = Intent(this, InvitedByActivity::class.java).apply {
                putExtra("phoneNumber", intent.getStringExtra("phoneNumber"))
            }
            startActivity(intent)
        }

    }

    override fun binding(): ViewBinding {
        return ActivitySettingUpBinding.inflate(layoutInflater)
    }

}