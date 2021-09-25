package com.example.toastgoand.home.startclan

import android.os.Bundle
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.viewbinding.ViewBinding
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.ToastgoApplication
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.databinding.ActivityStartClanBinding
import com.example.toastgoand.home.clanhub.components.MyFriendItem
import com.example.toastgoand.network.myfriends.MyFriendsDataClass
import com.example.toastgoand.network.userdetails.User
import com.example.toastgoand.network.userdetails.UserDetailsDataClass
import com.example.toastgoand.uibits.HeaderOtherScreens
import com.google.accompanist.insets.ProvideWindowInsets
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

                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "friendslist") {
                    composable("friendslist") {
                        FriendsListPage(
                            friendsList = myFriendsListHere,
                            backHandle = onBackPressedHere(),
                            navController = navController
                        )
                    }
                    composable("contactslist") {
                        ContactsListPage(
                            contactsList = deetsHere.user.contact_list,
                            backHandle = onBackPressedHere(),
                            navController = navController
                        )
                    }
                    composable("nameclan") {
                        NameClanPage(
                            backHandle = onBackPressedHere(),
                            navController = navController
                        )
                    }
                    composable("makingclan") {
                        MakingClanPage(
                            backHandle = onBackPressedHere(),
                            navController = navController
                        )
                    }
                }

            }
        }
    }

    @Composable
    fun FriendsListPage(friendsList: List<MyFriendsDataClass>, backHandle: Unit, navController: NavController) {
        val textState = remember { mutableStateOf(TextFieldValue()) }

        AyeTheme() {

            ProvideWindowInsets() {
                Scaffold(
                    topBar = {
                        HeaderOtherScreens(
                            modifier = Modifier.fillMaxWidth(),
                            title = "",
                            onBackIconPressed = { backHandle }
                        )
                    },
                    floatingActionButtonPosition = FabPosition.Center,
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = { navController.navigate("contactslist") },
                            modifier = Modifier
                                .padding(horizontal = 25.dp)
                                .size(60.dp),
                            backgroundColor = Color.Cyan,
                        ) {
                            Icon(
                                imageVector = FeatherIcons.ArrowRight,
                                contentDescription = "last month",
                                modifier = Modifier.size(30.dp)
                            )
                        }
                    }
                ) { contentPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colors.background),
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        TextField(
                            modifier = Modifier
                                .background(MaterialTheme.colors.surface)
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
                        LazyColumn(modifier = Modifier.background(MaterialTheme.colors.background)) {

                            items(
                                items = friendsList,
                                itemContent = {
                                    MyFriendItem(it)
                                })
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun ContactsListPage(contactsList: String, backHandle: Unit, navController: NavController) {
        val textState = remember { mutableStateOf(TextFieldValue()) }

        AyeTheme() {
            ProvideWindowInsets() {
                Scaffold(
                    topBar = {
                        HeaderOtherScreens(
                            modifier = Modifier.fillMaxWidth(),
                            title = "",
                            onBackIconPressed = { backHandle }
                        )
                    },
                    floatingActionButtonPosition = FabPosition.Center,
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = { navController.navigate("nameclan") },
                            modifier = Modifier
                                .padding(horizontal = 25.dp)
                                .size(60.dp),
                            backgroundColor = Color.Cyan,
                        ) {
                            Icon(
                                imageVector = FeatherIcons.ArrowRight,
                                contentDescription = "last month",
                                modifier = Modifier.size(30.dp)
                            )
                        }
                    }
                ) { contentPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colors.background),
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        TextField(
                            modifier = Modifier
                                .background(MaterialTheme.colors.surface)
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
                        LazyColumn(modifier = Modifier.background(MaterialTheme.colors.background)) {
//                                val contactsParsed = Gson().fromJson<List<ContactItem>>(
//                                    deetsHere.user.contact_list,
//                                    ContactItem::class.java
//                                )

//                                val simpleContactsString =
//                                    deetsHere.user.contact_list.drop(1).dropLast(1)

//                        if (contactsString.length > 10) {
//                            Log.i("invitepeople", contactsString?.slice(0..10))
////                                    val parserHere = Json {
////                                        isLenient = true; ignoreUnknownKeys = true; encodeDefaults =
////                                        false;
////                                    }
////                                    val contactsListHere =
////                                        parserHere.decodeFromString<ArrayList<ContactItem>>(
////                                            contactsString
////                                        )
////                                    decodeFromString<ArrayList<ContactItem>>(deetsHere.user.contact_list)
////                                    Log.i("invitepeople list", contactsListHere.toString())
//                        }
//
//                        Log.i("invitepeople", contactsString)
//
////                                items(
////                                    items = contactsListHere,
////                                    itemContent = {
////                                        ContactItemRender(Json.decodeFromString(it))
////                                    })
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun NameClanPage(backHandle: Unit, navController: NavController) {
        val textState = remember { mutableStateOf(TextFieldValue()) }

        AyeTheme() {

            ProvideWindowInsets() {
                Scaffold(
                    topBar = {
                        HeaderOtherScreens(
                            modifier = Modifier.fillMaxWidth(),
                            title = "",
                            onBackIconPressed = { backHandle }
                        )
                    },
                    floatingActionButtonPosition = FabPosition.Center,
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = { navController.navigate("makingclan") },
                            modifier = Modifier
                                .padding(horizontal = 25.dp)
                                .size(60.dp),
                            backgroundColor = Color.Cyan,
                        ) {
                            Icon(
                                imageVector = FeatherIcons.ArrowRight,
                                contentDescription = "last month",
                                modifier = Modifier.size(30.dp)
                            )
                        }
                    }
                ) { contentPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colors.background),
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        TextField(
                            modifier = Modifier
                                .background(MaterialTheme.colors.surface)
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

                    }
                }
            }
        }
    }

    @Composable
    fun MakingClanPage(backHandle: Unit, navController: NavController) {

        AyeTheme() {

            ProvideWindowInsets() {
                Scaffold(
                    topBar = {
                        HeaderOtherScreens(
                            modifier = Modifier.fillMaxWidth(),
                            title = "",
                            onBackIconPressed = { backHandle }
                        )
                    }
                ) { contentPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colors.background),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Text("making the clan")

                    }
                }
            }
        }
    }

    override fun binding(): ViewBinding {
        return ActivityStartClanBinding.inflate(layoutInflater)
    }
}