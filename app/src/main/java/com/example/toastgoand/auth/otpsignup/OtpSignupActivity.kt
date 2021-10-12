package com.example.toastgoand.auth.otpsignup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.R
import com.example.toastgoand.ToastgoApplication
import com.example.toastgoand.auth.loginsetup.LoginSetupViewModel
import com.example.toastgoand.auth.loginsetup.LoginSetupViewModelFactory
import com.example.toastgoand.auth.network.OtpSignUpApi
import com.example.toastgoand.auth.network.dataclasses.OtpSignUpDataClass
import com.example.toastgoand.auth.settingup.SettingUpActivity
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.databinding.ActivityOtpSignupBinding
import com.example.toastgoand.home.LandingActivity
import com.example.toastgoand.splash.SplashActivity
import com.example.toastgoand.uibits.LoaderDialog
import compose.icons.FeatherIcons
import compose.icons.feathericons.ArrowRight
import kotlinx.coroutines.launch

class OtpSignupActivity : BaseActivity() {
    private lateinit var binding: ActivityOtpSignupBinding

    private val viewModel: OtpSignupViewModel by viewModels {
        OtpSignupViewModelFactory(LandingActivity().repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = viewBinding as ActivityOtpSignupBinding

        binding.firstPinView.requestFocus()

        binding.backIcon.setOnClickListener {
            onBackPressed()
        }

        binding.otpSignupModel = viewModel

        intent.getStringExtra("phoneNumber")?.let { viewModel.checkInvitedOrNot(it) }
        intent.getStringExtra("phoneNumber")?.let { viewModel.getUserDetailsHere(it) }


        binding.nextImageButton.setOnClickListener {
            val intent = Intent(this, SettingUpActivity::class.java).apply {
                putExtra("phoneNumber", intent.getStringExtra("phoneNumber"))
            }
            startActivity(intent)
            overridePendingTransition(
                R.anim.slide_from_right,
                R.anim.slide_out_left
            )
        }

        binding.composeView.setContent {
            AyeTheme() {

                val context = LocalContext.current
                val composableScope = rememberCoroutineScope()

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
                                var otp = binding.firstPinView.text

                                var payloadHere = OtpSignUpDataClass(
                                    phone = intent.getStringExtra("phoneNumber"),
                                    password = otp.toString()
                                )

                                composableScope.launch {
                                    try {
                                        OtpSignUpApi.retrofitService.newUserOtpCheck(
                                            data = payloadHere
                                        )
                                        val intent =
                                            Intent(context, SettingUpActivity::class.java).apply {
                                                putExtra(
                                                    "phoneNumber",
                                                    intent.getStringExtra("phoneNumber")
                                                )
                                                putExtra(
                                                    "invitedCheckDataClub",
                                                    viewModel.invitedData.value?.invited_to_clubs_id.toString()
                                                )
                                                putExtra(
                                                    "invitedCheckDataUser",
                                                    viewModel.invitedData.value?.invited_by_user.toString()
                                                )
                                                putExtra(
                                                    "userid",
                                                    viewModel.userDetails.value?.user?.id.toString()
                                                )
                                                putExtra(
                                                    "countryIndicator",
                                                    intent.getStringExtra("countryIndicator")
                                                )
                                            }
                                        startActivity(intent)
                                        overridePendingTransition(
                                            R.anim.slide_from_right,
                                            R.anim.slide_out_left
                                        )
                                    } catch (e: Exception) {
                                        Log.i("detailssignup", e.toString())
                                    }
                                }
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
        return ActivityOtpSignupBinding.inflate(layoutInflater)
    }
}