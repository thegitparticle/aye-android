package com.example.toastgoand.home.myprofile

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.viewbinding.ViewBinding
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.R
import com.example.toastgoand.ToastgoApplication
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.databinding.ActivityMyProfileBinding
import com.example.toastgoand.home.myprofile.components.ButtonsList
import com.example.toastgoand.home.myprofile.components.Details
import com.example.toastgoand.network.userdetails.User
import com.example.toastgoand.network.userdetails.UserDetailsDataClass
import com.example.toastgoand.uibits.HeaderOtherScreens
import com.google.accompanist.insets.ProvideWindowInsets

class MyProfileActivity : BaseActivity() {
    private lateinit var binding: ActivityMyProfileBinding

    private val viewModel: MyProfileViewModel by viewModels {
        MyProfileViewModelFactory(
            (this.application as ToastgoApplication).repository
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = viewBinding as ActivityMyProfileBinding

        fun onBackPressedHere() {
            onBackPressed()
            overridePendingTransition(
                R.anim.slide_up_enter,
                R.anim.slide_down_exit
            )
        }

        setContent {
            AyeTheme {
                val deetsHere: UserDetailsDataClass by viewModel.deets.observeAsState(
                    UserDetailsDataClass(
                        bio = "", image = "", user = User(
                            phone = "",
                            full_name = "",
                            id = 0,
                            clubs_joined_by_user = "",
                            number_of_clubs_joined = 0,
                            contact_list = "",
                            total_frames_participation = 0,
                            country_code_of_user = "",
                            contact_list_sync_status = false,
                            username = ""
                        ), id = 0
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
                                .fillMaxHeight()
                                .background(AyeTheme.colors.uiBackground),
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Details(userDeets = deetsHere)
                            ButtonsList(
                                clubsNumber = deetsHere.user.number_of_clubs_joined.toString(),
                                framesNumber = deetsHere.user.total_frames_participation.toString(),
                                editOnPressed = {
                                    context.startActivity(
                                        Intent(
                                            context,
                                            EditProfileActivity::class.java
                                        ).apply {
                                            putExtra("olddp", deetsHere.image)
                                            putExtra("userid", deetsHere.user.id)
                                        })
                                },
                                settingsOnPressed = {
                                    context.startActivity(
                                        Intent(
                                            context,
                                            SettingsActivity::class.java
                                        ).apply {
                                            putExtra("olddp", deetsHere.image)
                                            putExtra("userid", deetsHere.user.id)
                                        })

                                }
                            )
                        }
                    }
                }
            }
        }
    }

    override fun binding(): ViewBinding {
        return ActivityMyProfileBinding.inflate(layoutInflater)
    }
}