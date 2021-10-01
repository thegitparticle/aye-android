package com.example.toastgoand.home.clanhub.clanaddpeople

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.R
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.viewbinding.ViewBinding
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.ToastgoApplication
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.databinding.ActivityClanAddPeopleBinding
import com.example.toastgoand.home.clanhub.components.MyFriendItem
import com.example.toastgoand.home.clanhub.network.AddFriendToClanApi
import com.example.toastgoand.home.startclan.StartClanInviteContactsActivity
import com.example.toastgoand.network.myfriends.MyFriendsDataClass
import com.example.toastgoand.network.userdetails.User
import com.example.toastgoand.network.userdetails.UserDetailsDataClass
import com.example.toastgoand.uibits.HeaderOtherScreens
import com.google.accompanist.insets.ProvideWindowInsets
import compose.icons.FeatherIcons
import compose.icons.feathericons.ArrowRight
import kotlinx.coroutines.launch

class ClanAddPeopleActivity : BaseActivity() {

    private lateinit var binding: ActivityClanAddPeopleBinding

    private val viewModel: ClanAddPeopleViewModel by viewModels {
        ClanAddPeopleViewModelFactory(
            (this.application as ToastgoApplication).repository,
            (this.application as ToastgoApplication).repositoryMyFriends
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = viewBinding as ActivityClanAddPeopleBinding


        fun onBackPressedHere() {
            onBackPressed()
        }

        val addedFriends = mutableListOf<MyFriendsDataClass>()

        fun addSelectedToList(item: MyFriendsDataClass) {
            addedFriends.add(item)
        }

        fun removeSelectedToList(item: MyFriendsDataClass) {
            addedFriends.remove(item)
        }

        val clubname = intent.getStringExtra("clubname")
        val clubid = intent.getIntExtra("clubid", 0)


        setContent {
            AyeTheme {
                val friendsListHere: List<MyFriendsDataClass> by viewModel.myfriends.observeAsState(
                    listOf<MyFriendsDataClass>()
                )

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
                val composableScope = rememberCoroutineScope()

                val textState = remember { mutableStateOf(TextFieldValue()) }


                ProvideWindowInsets() {
                    Scaffold(
                        topBar = {
                            HeaderOtherScreens(
                                modifier = Modifier.fillMaxWidth(),
                                title = "",
                                onBackIconPressed = { onBackPressedHere() }
                            )
                        },
                        floatingActionButtonPosition = FabPosition.Center,
                        floatingActionButton = {
                            FloatingActionButton(
                                onClick = {
                                    for (item in addedFriends) {
                                        composableScope.launch {
                                            try {
                                                AddFriendToClanApi.retrofitService.addFriendToClan(
                                                    adduserid = item.friend_user_id.toString(),
                                                    clubid = clubid.toString()
                                                )
                                            } catch (e: Exception) {
                                                Log.i("addpersontoclub", e.toString())
                                            }
                                        }
                                    }
                                    onBackPressedHere()
                                },
                                modifier = Modifier
                                    .padding(horizontal = 25.dp)
                                    .size(60.dp),
                                backgroundColor = Color.Cyan,
                            ) {
                                Text(
                                    text = "add them",
                                    style = MaterialTheme.typography.body2,
                                    color = AyeTheme.colors.uiBackground
                                )
                            }
                        }
                    ) { contentPadding ->
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(AyeTheme.colors.uiBackground),
                            verticalArrangement = Arrangement.SpaceBetween,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            TextField(
                                modifier = Modifier
                                    .background(AyeTheme.colors.uiSurface)
                                    .clip(
                                        RoundedCornerShape(corner = CornerSize(10.dp))
                                    )
                                    .padding(vertical = 10.dp)
                                    .fillMaxWidth(0.9f),
                                value = textState.value,
                                onValueChange = { textState.value = it },
                                textStyle = MaterialTheme.typography.body2,
                                singleLine = true,
                                placeholder = {
                                    Text(
                                        text = "",
                                        style = MaterialTheme.typography.body2
                                    )
                                },
                            )
                            Text("The textfield has this text: " + textState.value.text)
                            LazyColumn(
                                modifier = Modifier
                                    .background(AyeTheme.colors.uiBackground)
                                    .fillMaxWidth()
                            ) {

                                items(
                                    items = friendsListHere,
                                    itemContent = {
                                        MyFriendItem(it, ::addSelectedToList, ::removeSelectedToList)
                                    })
                            }
                        }
                    }

                }
            }
        }
    }

    override fun binding(): ViewBinding {
        return ActivityClanAddPeopleBinding.inflate(layoutInflater)
    }
}