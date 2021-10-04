package com.example.toastgoand.auth.detailssignup

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
import com.example.toastgoand.auth.network.DetailsSignUpApi
import com.example.toastgoand.auth.network.dataclasses.DetailsSignUpDataClass
import com.example.toastgoand.auth.otpsignup.OtpSignupActivity
import com.example.toastgoand.auth.welcome.WelcomeActivity
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.databinding.ActivityDetailsSignupBinding
import com.example.toastgoand.uibits.LoaderDialog
import compose.icons.FeatherIcons
import compose.icons.feathericons.ArrowRight
import kotlinx.android.synthetic.main.start_call_dialog.*
import kotlinx.coroutines.launch

class DetailsSignupActivity : BaseActivity() {
    private lateinit var binding: ActivityDetailsSignupBinding

    private lateinit var viewModel: DetailsSignupViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = viewBinding as ActivityDetailsSignupBinding

        binding.backIcon.setOnClickListener {
            onBackPressed()
        }

        viewModel = ViewModelProvider(this).get(DetailsSignupViewModel::class.java)
        binding.detailsSignupModel = viewModel

        var phoneNumber = intent.getStringExtra("phoneNumber")
        var countryIndicator = intent.getStringExtra("countryIndicator")

        if (phoneNumber != null) {
            Log.i("settingupdebug phone", phoneNumber)
        }
        if (countryIndicator != null) {
            Log.i("settingupdebug country indicator", countryIndicator)
        }

        viewModel.detailsposted.observe(this, {response ->
            if (response) {
                val intent =
                    Intent(this, OtpSignupActivity::class.java).apply {
                        putExtra(
                            "phoneNumber",
                            intent.getStringExtra("phoneNumber")
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
            }
        })

        binding.composeView.setContent {
            AyeTheme() {

                val context = LocalContext.current
                val composableScope = rememberCoroutineScope()

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    val isDialogOpen = remember { mutableStateOf(false) }
                    val detailsposted = remember { mutableStateOf(false) }

                    Log.i("signupdebug", "sign up de bug log working")

                    Icon(
                        imageVector = FeatherIcons.ArrowRight,
                        contentDescription = "next screen",
                        modifier = Modifier
                            .size(30.dp)
                            .clickable {

                                var full_name = binding.editTextTextPersonName.text
                                var user_name = binding.editTextTextPersonName2.text
                                var empty_object = mapOf<String, String>()

                                var payloadHere = DetailsSignUpDataClass(
                                    phone = intent.getStringExtra("phoneNumber"),
                                    full_name = full_name.toString(),
                                    username = user_name.toString(),
                                    profile = empty_object
                                )

                                viewModel.sendDetailsOfNewUser(payloadHere)
                                isDialogOpen.value = true
                            }
                            .background(color = AyeTheme.colors.textSecondary),
                    )

                    LoaderDialog(isDialogOpen)
                }
            }
        }

//        binding.nextImageButton.setOnClickListener {
//            val intent = Intent(this, OtpSignupActivity::class.java).apply {
//                putExtra("phoneNumber", intent.getStringExtra("phoneNumber"))
//            }
//            startActivity(intent)
//            overridePendingTransition(
//                R.anim.slide_from_right ,
//                R.anim.slide_out_left
//            )
//        }

//        viewModel.phoneCheck.value?.let { it1 -> Log.i("EnterPhoneViewModelX", it1) }
//
//        viewModel.phoneCheck.observe(this, Observer { response ->
//            Log.i("EnterPhoneViewModelX", response)
//        })
//
//        binding.nextImageButton.setOnClickListener {
//            viewModel.getUserDetailsHere(countryCode + phoneNumber.toString())
//        }

    }

    override fun binding(): ViewBinding {
        return ActivityDetailsSignupBinding.inflate(layoutInflater)
    }
}