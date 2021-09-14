package com.example.toastgoand.home.startclan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.ui.res.imageResource
import androidx.viewbinding.ViewBinding
import coil.compose.rememberImagePainter
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.R
import com.example.toastgoand.ToastgoApplication
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.databinding.ActivityMyProfileBinding
import com.example.toastgoand.databinding.ActivityStartClanBinding
import com.example.toastgoand.home.aye.TheAyeViewModel
import com.example.toastgoand.home.aye.TheAyeViewModelFactory
import com.example.toastgoand.home.directs.components.DirectItem
import com.example.toastgoand.home.directs.components.NudgeToItem
import com.example.toastgoand.home.myprofile.MyProfileViewModel
import com.example.toastgoand.home.startclan.components.FriendsListItem
import com.example.toastgoand.network.myfriends.MyFriendsDataClass
import com.example.toastgoand.network.nudgelist.NudgeToDataClass
import com.example.toastgoand.network.userdetails.User
import com.example.toastgoand.network.userdetails.UserDetailsDataClass
import com.example.toastgoand.uibits.TopHeaderModals
import compose.icons.FeatherIcons
import compose.icons.feathericons.ChevronDown
import spencerstudios.com.bungeelib.Bungee

class StartClanActivity : BaseActivity() {
    private lateinit var binding: ActivityStartClanBinding

    private val viewModel: StartClanViewModel by viewModels {
        StartClanViewModelFactory(
            (this.application as ToastgoApplication).repositoryMyFriends,
            (this.application as ToastgoApplication).repository
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = viewBinding as ActivityStartClanBinding

        fun onBackPressedHere() {
            onBackPressed()
            Bungee.slideDown(this)
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

            val myFriendsListHere: List<MyFriendsDataClass> by viewModel.friendsList.observeAsState(
                listOf<MyFriendsDataClass>()
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
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Box(
                            Modifier
                                .fillMaxWidth()
                                .background(Color.Blue)
                        ) {
                            Text("talk to founder", modifier = Modifier.align(Alignment.TopStart))
                            LazyColumn(
                                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                                modifier = Modifier.fillMaxSize()
                            ) {
                                items(
                                    items = myFriendsListHere,
                                    itemContent = {
                                        FriendsListItem(it)
                                    })
                            }
                        }
                    }
                }
            }
        }
        }
    }

    override fun binding(): ViewBinding {
        return ActivityStartClanBinding.inflate(layoutInflater)
    }
}