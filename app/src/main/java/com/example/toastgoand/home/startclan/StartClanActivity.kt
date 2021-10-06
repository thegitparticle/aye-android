package com.example.toastgoand.home.startclan

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.viewbinding.ViewBinding
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.R
import com.example.toastgoand.ToastgoApplication
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.databinding.ActivityStartClanBinding
import com.example.toastgoand.home.clanhub.clanaddpeople.ClanAddPeopleActivity
import com.example.toastgoand.home.clanhub.components.MyFriendItem
import com.example.toastgoand.home.startclan.components.*
import com.example.toastgoand.network.myfriends.MyFriendsDataClass
import com.example.toastgoand.network.userdetails.User
import com.example.toastgoand.network.userdetails.UserDetailsDataClass
import com.example.toastgoand.uibits.HeaderOtherScreens
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.statusBarsPadding
import compose.icons.FeatherIcons
import compose.icons.feathericons.ArrowRight
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

        val addedFriends = mutableListOf<MyFriendsDataClass>()

        fun addSelectedToList(item: MyFriendsDataClass) {
            addedFriends.add(item)
        }

        fun removeSelectedToList(item: MyFriendsDataClass) {
            addedFriends.remove(item)
        }

        fun onBackPressedHere() {
            onBackPressed()
        }

        setContent {
            AyeTheme {

                val context = LocalContext.current

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

                val textState = remember { mutableStateOf(TextFieldValue()) }

                val friendsFiltered = myFriendsListHere.filter { it ->
                    val word_here = it.name.lowercase()
                    word_here.contains(textState.value.text.lowercase(), false)
                }

                Scaffold(
                    modifier = Modifier
                        .navigationBarsWithImePadding()
                        .statusBarsPadding(),
                    topBar = {
                        HeaderOtherScreens(
                            modifier = Modifier.fillMaxWidth(),
                            title = "choose friends",
                            onBackIconPressed = { onBackPressedHere() }
                        )
                    },
                    floatingActionButtonPosition = FabPosition.Center,
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = {
                                val intent = Intent(
                                    context,
                                    StartClanInviteContactsActivity::class.java
                                ).apply {
                                    putExtra("addedfriends", addedFriends.toString())
                                }
                                startActivity(intent)
                                overridePendingTransition(
                                    R.anim.slide_from_right,
                                    R.anim.slide_left_exit
                                )
                            },
                            modifier = Modifier
                                .padding(horizontal = 25.dp),
                            backgroundColor = AyeTheme.colors.appLeadVariant,
                        ) {
                            Text(
                                text = "next",
                                style = MaterialTheme.typography.subtitle2,
                                color = AyeTheme.colors.uiBackground,
                                modifier = Modifier.padding(horizontal = 20.dp)
                            )
                        }
                    },
                    content = { innerPadding ->
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .background(AyeTheme.colors.uiBackground),
                            verticalArrangement = Arrangement.SpaceBetween,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            TextField(
                                modifier = Modifier
                                    .clip(
                                        RoundedCornerShape(corner = CornerSize(20.dp))
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
                                colors = TextFieldDefaults.textFieldColors(
                                    backgroundColor = AyeTheme.colors.uiSurface,
                                    cursorColor = AyeTheme.colors.textPrimary.copy(0.5f),
                                    textColor = AyeTheme.colors.textPrimary,
                                    placeholderColor = AyeTheme.colors.textPrimary.copy(0.5f),
                                    focusedLabelColor = AyeTheme.colors.uiSurface,
                                    unfocusedLabelColor = AyeTheme.colors.uiSurface,
                                    focusedIndicatorColor = AyeTheme.colors.uiSurface,
                                    unfocusedIndicatorColor = AyeTheme.colors.uiSurface,
                                )
                            )
                            Spacer(modifier = Modifier.size(20.dp))
                            LazyColumn(
                                modifier = Modifier
                                    .background(AyeTheme.colors.uiBackground)
                                    .fillMaxWidth()
                            ) {

                                items(
                                    items = friendsFiltered,
                                    itemContent = {
                                        FriendsListItem(
                                            it,
                                            ::addSelectedToList,
                                            ::removeSelectedToList
                                        )
                                    })
                                item {
                                    Spacer(modifier = Modifier.size(90.dp))
                                }
                            }
                        }

                    }
                )
            }
        }
    }

    override fun binding(): ViewBinding {
        return ActivityStartClanBinding.inflate(layoutInflater)
    }
}