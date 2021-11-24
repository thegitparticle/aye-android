package com.example.toastgoand.home.startclan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import com.example.toastgoand.ToastgoApplication
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.databinding.ActivityStartClanBinding
import com.example.toastgoand.home.LandingActivity
import com.example.toastgoand.home.startclan.network.StartClanApi
import com.example.toastgoand.home.startclan.network.StartClanDataClass
import com.example.toastgoand.network.userdetails.User
import com.example.toastgoand.network.userdetails.UserDetailsDataClass
import com.example.toastgoand.splash.SplashActivity
import com.example.toastgoand.uibits.HeaderOtherScreens
import compose.icons.FeatherIcons
import compose.icons.feathericons.ArrowRight
import kotlinx.coroutines.launch
import kotlin.Exception

class StartClanNameActivity : BaseActivity() {

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

                val addedFriends = intent.getStringExtra("addedfriends")

                val textState = remember { mutableStateOf(TextFieldValue()) }

                val showNameSetUp = remember { mutableStateOf(true) }

                var responseClanId = remember { mutableStateOf("") }

                val composableScope = rememberCoroutineScope()
                fun startClan() {
                    val name_here = textState.value.text
                    val admin_here = deetsHere.user.id
                    val members_here = deetsHere.user.id.toString()

                    val payload_here: StartClanDataClass =
                        StartClanDataClass(name_here, members_here, admin_here)

                    composableScope.launch {
                        try {
                            val responseStartClan =
                                StartClanApi.retrofitService.startClan(payload_here)

                            responseClanId.value = responseStartClan.toString()

                            Log.i("startclanlog", responseClanId.value)

                        } catch (e: Exception) {
                            Log.i("startclanlog", e.toString())
                        }
                    }

                }

                Scaffold(
                    topBar = {
                        HeaderOtherScreens(
                            modifier = Modifier.fillMaxWidth(),
                            title = "name of clan",
                            onBackIconPressed = { onBackPressedHere() }
                        )
                    },
                ) { contentPadding ->
                    if (showNameSetUp.value) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .background(AyeTheme.colors.uiBackground),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Column(
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
                                Button(
                                    onClick = {
                                        startClan()
                                        showNameSetUp.value = false
                                    },
                                    colors = ButtonDefaults.textButtonColors(
                                        backgroundColor = AyeTheme.colors.textSecondary,
                                    ),
                                    shape = RoundedCornerShape(30.dp),
                                    modifier = Modifier
                                        .padding(vertical = 30.dp)
                                        .height(60.dp)
                                        .width(160.dp),
                                ) {
                                    Text(
                                        "start clan",
                                        style = MaterialTheme.typography.subtitle1,
                                        color = AyeTheme.colors.uiBackground
                                    )
                                }
                            }
                        }
                    } else {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .background(AyeTheme.colors.uiBackground),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Text("setting up clan " + textState.value.text)

                            Button(
                                onClick = {
                                    context?.startActivity(
                                        Intent(
                                            context,
                                            LandingActivity::class.java
                                        ).apply { })
                                },
                                colors = ButtonDefaults.textButtonColors(
                                    backgroundColor = AyeTheme.colors.success,
                                ),
                                shape = RoundedCornerShape(30.dp),
                                modifier = Modifier
                                    .padding(vertical = 30.dp)
                                    .height(60.dp)
                                    .width(160.dp),
                            ) {
                                Text(
                                    "done",
                                    style = MaterialTheme.typography.subtitle1,
                                    color = AyeTheme.colors.uiBackground
                                )
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