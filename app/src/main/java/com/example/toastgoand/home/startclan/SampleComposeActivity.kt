package com.example.toastgoand.home.startclan

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.toastgoand.ToastgoApplication
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.home.startclan.components.ContactsListPage
import com.example.toastgoand.home.startclan.components.FriendsListPage
import com.example.toastgoand.home.startclan.components.MakingClanPage
import com.example.toastgoand.home.startclan.components.NameClanPage
import com.example.toastgoand.home.startclan.ui.theme.ToastgoandNTheme
import com.example.toastgoand.network.myfriends.MyFriendsDataClass
import com.example.toastgoand.network.userdetails.User
import com.example.toastgoand.network.userdetails.UserDetailsDataClass

class SampleComposeActivity : ComponentActivity() {

    private val viewModel: StartClanViewModel by viewModels {
        StartClanViewModelFactory(
            (this.application as ToastgoApplication).repositoryMyFriends,
            (this.application as ToastgoApplication).repository
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fun onBackPressedHere() {
            onBackPressed()
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

                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()

                Column(modifier = Modifier.fillMaxSize()) {
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
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ToastgoandNTheme {
        Greeting("Android")
    }
}