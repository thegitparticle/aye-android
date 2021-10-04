package com.example.toastgoand.auth.enterphone

import android.content.Intent
import android.os.Bundle
import android.text.Editable
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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.R
import com.example.toastgoand.auth.detailssignup.DetailsSignupActivity
import com.example.toastgoand.auth.otplogin.OtpLoginActivity
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.databinding.ActivityEnterPhoneBinding
import com.example.toastgoand.uibits.LoaderDialog
import com.hbb20.CountryCodePicker.OnCountryChangeListener
import compose.icons.FeatherIcons
import compose.icons.feathericons.ArrowRight


class EnterPhoneActivity : BaseActivity() {

    private lateinit var binding: ActivityEnterPhoneBinding

    private lateinit var viewModel: EnterPhoneViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = viewBinding as ActivityEnterPhoneBinding

        binding.editTextPhone.requestFocus()

        binding.backIcon.setOnClickListener {
            onBackPressed()
        }

        val phoneNumber: Editable? = binding.editTextPhone.getText()

        var countryCode: String = binding.ccp.selectedCountryCodeWithPlus
        var countryIndicator: String = binding.ccp.selectedCountryName

        binding.ccp.setOnCountryChangeListener(OnCountryChangeListener {
            countryCode = binding.ccp.selectedCountryCodeWithPlus
        })

        viewModel = ViewModelProvider(this).get(EnterPhoneViewModel::class.java)
        binding.enterPhoneModel = viewModel

        viewModel.phoneCheck.observe(this, Observer { response ->
            Log.i("EnterPhoneViewModelX", response)
            if (response.isNotEmpty()) {
                if (response == "True") {
                    val intent =
                        Intent(this, OtpLoginActivity::class.java).apply {
                            putExtra(
                                "phoneNumber",
                                countryCode + phoneNumber.toString()
                            )
                            putExtra(
                                "countryIndicator",
                                countryIndicator
                            )
                        }
                    startActivity(intent)
                    overridePendingTransition(
                        R.anim.slide_from_right,
                        R.anim.slide_out_left
                    )
                } else {
                    val intent =
                        Intent(this, DetailsSignupActivity::class.java).apply {
                            putExtra(
                                "phoneNumber",
                                countryCode + phoneNumber.toString()
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

                val phoneCheckStatus: String by viewModel.phoneCheck.observeAsState("")
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
                                viewModel.checkPhoneNumberHere(countryCode + phoneNumber.toString())
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
        return ActivityEnterPhoneBinding.inflate(layoutInflater)
    }

}