package com.example.toastgoand.home.startclan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
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
import com.example.toastgoand.R
import com.example.toastgoand.ToastgoApplication
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.databinding.ActivityStartClanBinding
import com.example.toastgoand.home.LandingActivity
import com.example.toastgoand.home.invitepeopledirectly.ContactItemRender
import com.example.toastgoand.home.invitepeopledirectly.network.ContactsListItemDataClass
import com.example.toastgoand.splash.SplashActivity
import com.example.toastgoand.uibits.HeaderOtherScreens
import compose.icons.FeatherIcons
import compose.icons.feathericons.ArrowRight

class StartClanInviteContactsActivity : BaseActivity() {

    private lateinit var binding: ActivityStartClanBinding

    private val viewModel: StartClanViewModel by viewModels {
        StartClanViewModelFactory(
            LandingActivity().repositoryMyFriends,
            LandingActivity().repository
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = viewBinding as ActivityStartClanBinding

        val addedContactsHere = mutableListOf<ContactsListItemDataClass>()

        fun addSelectedToListHere(item: ContactsListItemDataClass) {
            addedContactsHere.add(item)
        }

        fun removeSelectedToListHere(item: ContactsListItemDataClass ) {
            addedContactsHere.remove(item)
        }

        fun onBackPressedHere() {
            onBackPressed()
        }

        setContent {
            AyeTheme() {

                val context = LocalContext.current

                val addedFriends = intent.getStringExtra("addedfriends")

                val contactsListHere: List<ContactsListItemDataClass> by viewModel.contactsList.observeAsState(
                    listOf<ContactsListItemDataClass>()
                )

                val composableScope = rememberCoroutineScope()

                val textState = remember { mutableStateOf(TextFieldValue()) }

                val contactsFiltered = contactsListHere.filter { it ->
                    val word_here = it.name.lowercase()
                    word_here.contains(textState.value.text.lowercase(), false)
                }

                Scaffold(
                    topBar = {
                        HeaderOtherScreens(
                            modifier = Modifier.fillMaxWidth(),
                            title = "choose contacts",
                            onBackIconPressed = { onBackPressedHere() }
                        )
                    },
                    floatingActionButtonPosition = FabPosition.Center,
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = {
                                val intent = Intent(
                                    context,
                                    StartClanNameActivity::class.java
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
                                items = contactsListHere,
                                itemContent = {
                                    ContactItemRender(
                                        it,
                                        ::addSelectedToListHere,
                                        ::removeSelectedToListHere
                                    )
                                }
                            )
                            item {
                                Spacer(modifier = Modifier.size(90.dp))
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