package com.example.toastgoand.home.aye

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.ui.graphics.imageFromResource
import androidx.ui.res.colorResource
import androidx.ui.res.imageResource
import androidx.viewbinding.ViewBinding
import coil.compose.rememberImagePainter
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.R
import com.example.toastgoand.ToastgoApplication
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.databinding.ActivityTheAyeBinding
import com.example.toastgoand.home.invitepeopledirectly.InvitePeopleDirectlyViewModel
import com.example.toastgoand.home.invitepeopledirectly.InvitePeopleDirectlyViewModelFactory
import com.example.toastgoand.home.myprofile.components.Details
import com.example.toastgoand.network.userdetails.User
import com.example.toastgoand.network.userdetails.UserDetailsDataClass
import com.example.toastgoand.uibits.TopHeaderModals
import compose.icons.FeatherIcons
import compose.icons.feathericons.ChevronDown
import spencerstudios.com.bungeelib.Bungee

class TheAyeActivity : BaseActivity() {
    private lateinit var binding: ActivityTheAyeBinding

    private val viewModel: TheAyeViewModel by viewModels {
        TheAyeViewModelFactory(
            (this.application as ToastgoApplication).repository
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = viewBinding as ActivityTheAyeBinding

        fun onBackPressedHere() {
            onBackPressed()
            Bungee.slideDown(this)
        }

        setContent {
            AyeTheme() {
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
                    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.SpaceBetween) {
                        val painter = painterResource(id = R.drawable.aye_logo)
                        Image(
                            painter = painter,
                            contentDescription = "aye logo",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .padding(8.dp)
                                .size(100.dp)
                        )
                        Column(modifier = Modifier.fillMaxWidth()) {
                            Box (
                                Modifier
                                    .fillMaxWidth()
                                    .background(Color.Blue)) {
                                Text("talk to founder", modifier = Modifier.align(Alignment.TopStart))
                            }
                        }
                    }
                }

            }
        }
    }

    override fun binding(): ViewBinding {
        return ActivityTheAyeBinding.inflate(layoutInflater)
    }
}