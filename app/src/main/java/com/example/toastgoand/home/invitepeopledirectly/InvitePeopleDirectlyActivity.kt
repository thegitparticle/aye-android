package com.example.toastgoand.home.invitepeopledirectly

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.ui.res.imageResource
import androidx.viewbinding.ViewBinding
import coil.compose.rememberImagePainter
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.R
import com.example.toastgoand.ToastgoApplication
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.databinding.ActivityInvitePeopleDirectlyBinding
import com.example.toastgoand.home.clantalk.components.CEntryFile
import com.example.toastgoand.home.clantalk.components.MessageMetaData
import com.example.toastgoand.home.directs.components.DirectItem
import com.example.toastgoand.home.myprofile.MyProfileViewModel
import com.example.toastgoand.home.myprofile.MyProfileViewModelFactory
import com.example.toastgoand.network.userdetails.User
import com.example.toastgoand.network.userdetails.UserDetailsDataClass
import com.example.toastgoand.uibits.HeaderOtherScreens
import com.example.toastgoand.uibits.TopHeaderModals
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.gson.Gson
import compose.icons.FeatherIcons
import compose.icons.feathericons.ChevronDown
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import spencerstudios.com.bungeelib.Bungee

class InvitePeopleDirectlyActivity : BaseActivity() {
    private lateinit var binding: ActivityInvitePeopleDirectlyBinding

    private val viewModel: InvitePeopleDirectlyViewModel by viewModels {
        InvitePeopleDirectlyViewModelFactory(
            (this.application as ToastgoApplication).repository,
            (this.application as ToastgoApplication).repositoryMyFriends
        )
    }

    @Serializable
    data class ContactItem(val name: String, val phone: String)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = viewBinding as ActivityInvitePeopleDirectlyBinding


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

                val textState = remember { mutableStateOf(TextFieldValue()) }


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
                            verticalArrangement = Arrangement.SpaceBetween, horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            TextField(
                                modifier = Modifier
                                    .background(MaterialTheme.colors.surface)
                                    .clip(
                                        RoundedCornerShape(corner = CornerSize(10.dp))
                                    )
                                    .padding(vertical = 10.dp).fillMaxWidth(0.9f),
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

                                val contactsString: String = deetsHere.user.contact_list

                                if (contactsString.length > 10) {
                                    Log.i("invitepeople", contactsString?.slice(0..10))
//                                    val parserHere = Json {
//                                        isLenient = true; ignoreUnknownKeys = true; encodeDefaults =
//                                        false;
//                                    }
//                                    val contactsListHere =
//                                        parserHere.decodeFromString<ArrayList<ContactItem>>(
//                                            contactsString
//                                        )
//                                    decodeFromString<ArrayList<ContactItem>>(deetsHere.user.contact_list)
//                                    Log.i("invitepeople list", contactsListHere.toString())
                                }

                                Log.i("invitepeople", contactsString)

//                                items(
//                                    items = contactsListHere,
//                                    itemContent = {
//                                        ContactItemRender(Json.decodeFromString(it))
//                                    })
                            }
                        }
                    }

                }

            }
        }
    }

    override fun binding(): ViewBinding {
        return ActivityInvitePeopleDirectlyBinding.inflate(layoutInflater)
    }
}