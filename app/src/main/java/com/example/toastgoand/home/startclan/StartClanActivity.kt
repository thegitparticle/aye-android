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
import com.example.toastgoand.home.startclan.components.ContactsListPage
import com.example.toastgoand.home.startclan.components.FriendsListPage
import com.example.toastgoand.home.startclan.components.MakingClanPage
import com.example.toastgoand.home.startclan.components.NameClanPage
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



            }
        }
    }

    override fun binding(): ViewBinding {
        return ActivityStartClanBinding.inflate(layoutInflater)
    }
}