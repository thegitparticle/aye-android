package com.example.toastgoand.home.startclan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.toastgoand.uibits.HeaderOtherScreens
import compose.icons.FeatherIcons
import compose.icons.feathericons.ArrowRight

class StartClanInviteContactsActivity : BaseActivity() {

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
            AyeTheme() {

                val context = LocalContext.current

                val addedFriends = intent.getStringExtra("addedfriends")

                val textState = remember { mutableStateOf(TextFieldValue()) }

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
                                val intent = Intent(
                                    context,
                                    StartClanNameActivity::class.java
                                ).apply {
                                    putExtra( "addedfriends",  addedFriends.toString())
                                }
                                startActivity(intent)
                                overridePendingTransition(
                                    R.anim.slide_from_right,
                                    R.anim.slide_left_exit
                                )
                            },
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
                            .fillMaxHeight()
                            .background(AyeTheme.colors.uiBackground),
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

    override fun binding(): ViewBinding {
        return ActivityStartClanBinding.inflate(layoutInflater)
    }
}