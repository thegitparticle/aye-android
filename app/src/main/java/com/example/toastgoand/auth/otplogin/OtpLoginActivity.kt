package com.example.toastgoand.auth.otplogin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.viewbinding.ViewBinding
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.auth.loginsetup.LoginSetupActivity
import com.example.toastgoand.auth.otplogin.network.OTPLoginDataClass
import com.example.toastgoand.auth.settingup.SettingUpActivity
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.databinding.ActivityOtpLoginBinding
import com.example.toastgoand.uibits.LoaderDialog
import compose.icons.FeatherIcons
import compose.icons.feathericons.ArrowRight

class OtpLoginActivity : BaseActivity() {
    private lateinit var binding: ActivityOtpLoginBinding

    private lateinit var viewModel: OtpLoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = viewBinding as ActivityOtpLoginBinding

        binding.firstPinView.requestFocus()

        viewModel = ViewModelProvider(this).get(OtpLoginViewModel::class.java)
        binding.otpLoginModel = viewModel

        val phoneNumberHere = intent.getStringExtra("phoneNumber")

        viewModel.otpCheck.observe(this, {response ->
            Log.i("OTPCheck", response)
            if (response.isNotEmpty()) {
                if (response == "worked") {
                    val intent =
                        Intent(this, LoginSetupActivity::class.java).apply {
                            putExtra(
                                "phoneNumber",
                                phoneNumberHere
                            )
                        }
                    startActivity(intent)
                }
            }
        })

        binding.composeView.setContent {
            AyeTheme() {

                val context = LocalContext.current

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val isDialogOpen = remember { mutableStateOf(false) }

                    Icon(
                        imageVector = FeatherIcons.ArrowRight,
                        contentDescription = "next screen",
                        modifier = Modifier
                            .size(30.dp)
                            .clickable {

                                val phone = phoneNumberHere
                                val password = binding.firstPinView.text.toString()

                                viewModel.sendOtpPayload(
                                    payload = OTPLoginDataClass(
                                        phone = phone,
                                        password = password
                                    )
                                )
                                isDialogOpen.value = true
                            }
                            .background(color = AyeTheme.colors.textSecondary),
                    )

                    LoaderDialog(isDialogOpen)
                }
            }
        }

//        viewModel.phoneCheck.value?.let { it1 -> Log.i("EnterPhoneViewModelX", it1) }
//
//        viewModel.phoneCheck.observe(this, Observer { response ->
//            Log.i("EnterPhoneViewModelX", response)
//        })
//
//        binding.nextImageButton.setOnClickListener {
//            intent.getStringExtra("phoneNumber")?.let { it1 -> viewModel.getUserDetailsHere(it1) }

//            val intent = Intent(this, LoginSetupActivity::class.java).apply {
//                putExtra("phoneNumber", intent.getStringExtra("phoneNumber"))
//            }
//            startActivity(intent)
//        }

    }

    override fun binding(): ViewBinding {
        return ActivityOtpLoginBinding.inflate(layoutInflater)
    }
}