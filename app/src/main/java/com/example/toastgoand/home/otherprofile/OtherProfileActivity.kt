package com.example.toastgoand.home.otherprofile

import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.databinding.ActivityOtherProfileBinding
import com.example.toastgoand.home.otherprofile.components.DetailsOtherProfile
import com.example.toastgoand.uibits.HeaderOtherScreens
import com.google.accompanist.insets.ProvideWindowInsets

class OtherProfileActivity : BaseActivity() {
    private lateinit var binding: ActivityOtherProfileBinding

    private lateinit var viewModel: OtherProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = viewBinding as ActivityOtherProfileBinding
        viewModel = ViewModelProvider(this).get(OtherProfileViewModel::class.java)

        val otheruserid = intent.getIntExtra("otheruserid", 0)

        Log.i("otherprofileviewing", otheruserid.toString())

        if (otheruserid > 0) {
            viewModel.getOtherProfileHere(otheruserid = otheruserid)
        }

        fun onBackPressedHere() {
            onBackPressed()
        }

        setContent {
            AyeTheme {
                val otherProfile: List<OtherProfileDataClass> by viewModel.otherProfile.observeAsState(
                    listOf(
                        OtherProfileDataClass(
                            user = DepthDetails(
                                username = "",
                                phone = "",
                                full_name = "",
                                id = 0,
                                clubs_joined_by_user = "",
                                number_of_clubs_joined = 0,
                                contact_list = "",
                                total_frames_participation = 0,
                                country_code_of_user = "",
                                contact_list_sync_status = false
                            ),
                            bio = "",
                            image = "",
                            id = 0
                        )
                    )
                )

                val context = LocalContext.current

                ProvideWindowInsets() {
                    Scaffold(
                        topBar = {
                            HeaderOtherScreens(
                                modifier = Modifier.fillMaxWidth(),
                                title = "",
                                onBackIconPressed = { onBackPressedHere() }
                            )
                        }
                    ) { contentPadding ->
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colors.background),
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            DetailsOtherProfile(userDeets = otherProfile[0])
                        }
                    }
                }
            }
        }
    }

    override fun binding(): ViewBinding {
        return ActivityOtherProfileBinding.inflate(layoutInflater)
    }
}

