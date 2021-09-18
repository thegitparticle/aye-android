package com.example.toastgoand.home.myprofile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.fragment.app.viewModels
import androidx.viewbinding.ViewBinding
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.R
import com.example.toastgoand.ToastgoApplication
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.databinding.ActivityMyProfileBinding
import com.example.toastgoand.databinding.ActivityTheAyeBinding
import com.example.toastgoand.home.aye.TheAyeViewModel
import com.example.toastgoand.home.clanframes.ClanFramesActivity
import com.example.toastgoand.home.clans.ClansViewModel
import com.example.toastgoand.home.clans.ClansViewModelFactory
import com.example.toastgoand.home.myprofile.components.Details
import com.example.toastgoand.network.myclans.MyClansDataClass
import com.example.toastgoand.network.userdetails.User
import com.example.toastgoand.network.userdetails.UserDetailsDataClass
import com.example.toastgoand.uibits.TopHeaderModals
import com.example.toastgoand.uibits.TopHeaderPlayScreens
import compose.icons.FeatherIcons
import compose.icons.feathericons.ChevronDown
import spencerstudios.com.bungeelib.Bungee

class MyProfileActivity : BaseActivity() {
    private lateinit var binding: ActivityMyProfileBinding

    private lateinit var viewModel2: MyProfileViewModel


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
            overridePendingTransition(R.anim.slide_up_enter,
                R.anim.slide_down_exit)
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

                Column(modifier = Modifier.fillMaxWidth()) {
                    TopHeaderModals(
                        modifier = Modifier.fillMaxWidth(),
                        title = {
                            Column(
                                modifier = Modifier.weight(1f),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "",
                                    style = MaterialTheme.typography.subtitle1
                                )
                            }
                        },
                        onLeftIconPressed = {},
                        actions = {
                            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                                Icon(
                                    imageVector = FeatherIcons.ChevronDown,
                                    modifier = Modifier
                                        .clickable(onClick = { onBackPressedHere() })
                                        .padding(horizontal = 12.dp, vertical = 16.dp)
                                        .height(24.dp),
                                    contentDescription = "go back"
                                )
                            }
                        }
                    )
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Details(userDeets = deetsHere)
                    }
                }

            }
        }
    }

    override fun binding(): ViewBinding {
        return ActivityMyProfileBinding.inflate(layoutInflater)
    }
}