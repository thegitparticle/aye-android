package com.example.toastgoand.auth.invitedby

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.auth.settingup.SettingUpViewModel
import com.example.toastgoand.databinding.ActivityInvitedByBinding
import com.example.toastgoand.databinding.ActivitySettingUpBinding

class InvitedByActivity : BaseActivity() {
    private lateinit var binding: ActivityInvitedByBinding

    private lateinit var viewModel: InvitedByViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = viewBinding as ActivityInvitedByBinding

        viewModel = ViewModelProvider(this).get(InvitedByViewModel::class.java)

    }

    override fun binding(): ViewBinding {
        return ActivityInvitedByBinding.inflate(layoutInflater)
    }

}