package com.example.toastgoand.auth.clancreate

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.R
import com.example.toastgoand.ToastgoApplication
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.databinding.ActivityClanCreatedBinding
import com.example.toastgoand.databinding.ActivityCreateClanBinding
import com.example.toastgoand.home.LandingActivity
import com.example.toastgoand.home.clanhub.network.InviteToClanSendApi
import com.example.toastgoand.home.invitepeopledirectly.ContactItemRender
import com.example.toastgoand.home.invitepeopledirectly.InvitePeopleDirectlyViewModelFactory
import com.example.toastgoand.home.invitepeopledirectly.network.ContactsListItemDataClass
import com.example.toastgoand.network.userdetails.User
import com.example.toastgoand.network.userdetails.UserDetailsDataClass
import com.example.toastgoand.prefhelpers.PrefHelper
import com.example.toastgoand.splash.SplashActivity
import com.example.toastgoand.uibits.HeaderOtherScreens
import com.example.toastgoand.uibits.LoadingComposeBit
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.gson.Gson
import kotlinx.coroutines.launch
import java.util.*

class CreateClanActivity : BaseActivity() {
    private lateinit var binding: ActivityCreateClanBinding

    private val viewModel: CreateClanViewModel by viewModels {
        CreateClanViewModelFactory(
            (this.application as ToastgoApplication).repository,
            (this.application as ToastgoApplication).repositoryMyFriends
        )
    }

    lateinit var prefHelper: PrefHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fun onBackPressedHere() {
            onBackPressed()
        }

        val addedContacts = arrayListOf<String>()
        val addedContacts1 = mutableListOf<ContactsListItemDataClass>()

        val gson = Gson()

        fun addSelectedToList(item: ContactsListItemDataClass) {
            addedContacts.add(item.phone)
        }

        fun removeSelectedToList(item: ContactsListItemDataClass) {
            addedContacts.remove(item.phone)
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

                val contactsListHere = viewModel.contactsList.observeAsState(
                    listOf<ContactsListItemDataClass>()
                )
                val composableScope = rememberCoroutineScope()

                val textState = remember { mutableStateOf(TextFieldValue()) }

                val contactsFiltered = contactsListHere.value.filter { it ->
                    val word_here = it.name.lowercase()
                    word_here.contains(textState.value.text.lowercase(), false)
                }


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
                                    for (item in addedContacts) {
                                        var stringList = gson.toJson(addedContacts)
                                        val intent = Intent(this, NameClanActivity::class.java).apply {
                                            putExtra("userid", viewModel.deets.value?.user?.id.toString())
                                            putExtra("chosencontactssize", addedContacts.size.toString())
                                            putExtra("chosencontacts", addedContacts)
                                        }
                                        startActivity(intent)
                                        overridePendingTransition(
                                            R.anim.slide_from_right ,
                                            R.anim.slide_out_left
                                        )


//                                        composableScope.launch {
//                                            try {
//                                                InviteToClanSendApi.retrofitService.inviteClanSendToClan(
//                                                    phonenumber = item.phone,
//                                                    userid = deetsHere.user.id.toString()
//                                                )
//                                            } catch (e: Exception) {
//                                                Log.i("invitepersonlogs", e.toString())
//                                            }
//                                        }
                                    }
                                    onBackPressedHere()
                                },
                                modifier = Modifier
                                    .padding(horizontal = 25.dp),
                                backgroundColor = AyeTheme.colors.appLeadVariant,
                            ) {
                                Text(
                                    text = "invite them",
                                    style = MaterialTheme.typography.subtitle2,
                                    color = AyeTheme.colors.uiBackground,
                                    modifier = Modifier.padding(horizontal = 20.dp)
                                )
                            }
                        }
                    ) { contentPadding ->
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
                            if (contactsListHere.value.isNotEmpty()) {
                                LazyColumn(modifier = Modifier.background(AyeTheme.colors.uiBackground)) {
                                    val contactsString: String = deetsHere.user.contact_list

                                    if (contactsString.length > 10) {
                                        Log.i("invitepeople", contactsString?.slice(0..10))
                                    }

                                    Log.i("invitepeople", contactsString)
                                    items(
                                        items = contactsFiltered,
                                        itemContent = {
                                            ContactItemRender(
                                                it, ::addSelectedToList,
                                                ::removeSelectedToList
                                            )
                                        })
                                    item {
                                        Spacer(modifier = Modifier.size(90.dp))
                                    }
                                }
                            } else {
                                Spacer(modifier = Modifier.size(200.dp))
                                LoadingComposeBit()
                                Spacer(modifier = Modifier.size(400.dp))
                            }
                        }
                    }

                }

            }
        }

    }

    override fun binding(): ViewBinding {
        return ActivityCreateClanBinding.inflate(layoutInflater)
    }

}