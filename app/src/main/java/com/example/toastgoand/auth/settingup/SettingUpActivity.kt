package com.example.toastgoand.auth.settingup

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.viewbinding.ViewBinding
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.R
import com.example.toastgoand.ToastgoApplication
import com.example.toastgoand.auth.invitedby.InvitedByActivity
import com.example.toastgoand.databinding.ActivitySettingUpBinding
import android.content.pm.PackageManager
import com.example.toastgoand.auth.LoginActivity_MembersInjector.create
import java.util.*
import kotlin.collections.ArrayList
import android.os.AsyncTask

import android.content.ContentResolver
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.alexstyl.contactstore.ContactStore
import com.example.toastgoand.auth.enterphone.EnterPhoneViewModel
import kotlinx.coroutines.InternalCoroutinesApi


class SettingUpActivity : BaseActivity() {
    private lateinit var binding: ActivitySettingUpBinding

    private lateinit var viewModel: SettingUpViewModel

    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(SettingUpViewModel::class.java)

        binding = viewBinding as ActivitySettingUpBinding

        val store = ContactStore.newInstance(application)

        val requestPermissionLauncher =
            registerForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) { isGranted: Boolean ->
                if (isGranted) {
                    Log.i("Permission: ", "Granted")
                    intent.getStringExtra("countryIndicator")?.let {
                        viewModel.uploadUserContacts(
                            userid = intent.getStringExtra("userid")!!,
                            countryIndicator = it,
                            store = store
                        )
                    }
                    binding.changingText.text = "setting up your profile ..."
                    binding.allowContactsButton.visibility = View.INVISIBLE
                    if (viewModel.uploaded.value == true) {
                        val intent = Intent(this, InvitedByActivity::class.java).apply {
                            putExtra("phoneNumber", intent.getStringExtra("phoneNumber"))
                            putExtra("invitedCheckData", intent.getStringExtra("invitedCheckData"))
                        }
                        startActivity(intent)
                        overridePendingTransition(
                            R.anim.slide_from_right,
                            R.anim.slide_out_left
                        )
                    } else {
                        var toast = Toast.makeText(this, "uploaded is not tru yet", Toast.LENGTH_LONG)
                        toast.show()
                    }
                } else {
                    Log.i("Permission: ", "Denied")
                    binding.changingText.text = "Aye! needs contacts to work"
                    binding.allowContactsButton.visibility = View.VISIBLE

                }
            }

        requestPermissionLauncher.launch(
            Manifest.permission.READ_CONTACTS
        )

        binding.allowContactsButton.setOnClickListener {

            requestPermissionLauncher.launch(
                Manifest.permission.READ_CONTACTS
            )

//            val intent = Intent(this, InvitedByActivity::class.java).apply {
//                putExtra("phoneNumber", intent.getStringExtra("phoneNumber"))
//            }
//            startActivity(intent)
//            overridePendingTransition(
//                R.anim.slide_from_right,
//                R.anim.slide_out_left
//            )
        }

    }

    override fun binding(): ViewBinding {
        return ActivitySettingUpBinding.inflate(layoutInflater)
    }

}