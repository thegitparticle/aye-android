package com.example.toastgoand.auth.otplogin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import com.example.toastgoand.R
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

        binding.backIcon.setOnClickListener {
            onBackPressed()
        }

        viewModel = ViewModelProvider(this).get(OtpLoginViewModel::class.java)
        binding.otpLoginModel = viewModel


        val phoneNumberHere = intent.getStringExtra("phoneNumber")
        if (phoneNumberHere != null) {
            viewModel.getUserDetailsHere(phone = phoneNumberHere)
        }
        val deetsHere = viewModel.deets

        var countryIndicator = intent.getStringExtra("countryIndicator")

        viewModel.otpCheck.observe(this, { response ->
            Log.i("OTPCheck", response)
            if (response.isNotEmpty()) {
                if (response == "worked") {
                    val intent =
                        Intent(this, LoginSetupActivity::class.java).apply {
                            putExtra(
                                "phoneNumber",
                                phoneNumberHere
                            )
                            putExtra(
                                "countryIndicator",
                                countryIndicator
                            )
                            putExtra(
                                "userid", viewModel.deets.value?.user?.id?.toString()
                            )
                        }
                    startActivity(intent)
                    overridePendingTransition(
                        R.anim.slide_from_right,
                        R.anim.slide_out_left
                    )
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

                    Box(
                        modifier = Modifier
                            .width(70.dp)
                            .background(AyeTheme.colors.iconBackground.copy(0.0f))
                            .clickable{

                                val phone = phoneNumberHere
                                val password = binding.firstPinView.text.toString()

                                viewModel.sendOtpPayload(
                                    payload = OTPLoginDataClass(
                                        phone = phone,
                                        password = password
                                    )
                                )
                                isDialogOpen.value = true
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            FeatherIcons.ArrowRight,
                            "next screen",
                            tint = AyeTheme.colors.uiSurface,
                            modifier = Modifier.size(35.dp),
                        )
                    }

                    LoaderDialog(isDialogOpen)
                }
            }
        }

    }

    override fun binding(): ViewBinding {
        return ActivityOtpLoginBinding.inflate(layoutInflater)
    }
}