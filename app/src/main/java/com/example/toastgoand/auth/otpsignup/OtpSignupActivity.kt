package com.example.toastgoand.auth.otpsignup

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
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
import com.example.toastgoand.auth.network.OtpSignUpApi
import com.example.toastgoand.auth.network.dataclasses.OtpSignUpDataClass
import com.example.toastgoand.auth.settingup.SettingUpActivity
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.databinding.ActivityOtpSignupBinding
import com.example.toastgoand.uibits.LoaderDialog
import compose.icons.FeatherIcons
import compose.icons.feathericons.ArrowRight
import kotlinx.coroutines.launch

class OtpSignupActivity : BaseActivity() {
    private lateinit var binding: ActivityOtpSignupBinding

    private lateinit var viewModel: OtpSignupViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = viewBinding as ActivityOtpSignupBinding

        binding.firstPinView.requestFocus()

        binding.backIcon.setOnClickListener {
            onBackPressed()
        }

        viewModel = ViewModelProvider(this).get(OtpSignupViewModel::class.java)
        binding.otpSignupModel = viewModel

        intent.getStringExtra("phoneNumber")?.let { viewModel.checkInvitedOrNot(it) }
        intent.getStringExtra("phoneNumber")?.let { viewModel.getUserDetailsHere(it) }


        binding.nextImageButton.setOnClickListener {
            val intent = Intent(this, SettingUpActivity::class.java).apply {
                putExtra("phoneNumber", intent.getStringExtra("phoneNumber"))
            }
            startActivity(intent)
            overridePendingTransition(
                R.anim.slide_from_right ,
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

                    Icon(
                        imageVector = FeatherIcons.ArrowRight,
                        contentDescription = "next screen",
                        modifier = Modifier
                            .size(30.dp)
                            .clickable {

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
                                        val intent = Intent(context, SettingUpActivity::class.java).apply {
                                            putExtra("phoneNumber", intent.getStringExtra("phoneNumber"))
                                            putExtra("invitedCheckData", viewModel.invitedData.toString())
                                            putExtra("userid", viewModel.userDetails.value?.user?.id.toString())
                                            putExtra(
                                                "countryIndicator",
                                                intent.getStringExtra("countryIndicator")
                                            )
                                        }
                                        startActivity(intent)
                                        overridePendingTransition(
                                            R.anim.slide_from_right ,
                                            R.anim.slide_out_left
                                        )
                                    } catch (e: Exception) {
                                        Log.i("detailssignup", e.toString())
                                    }
                                }

                                isDialogOpen.value = true
                            }
                            .background(color = AyeTheme.colors.textSecondary),
                    )

                    LoaderDialog(isDialogOpen)
                }
            }
        }


    }

    override fun binding(): ViewBinding {
        return ActivityOtpSignupBinding.inflate(layoutInflater)
    }
}