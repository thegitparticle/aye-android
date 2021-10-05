package com.example.toastgoand.auth.clancreate

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.viewbinding.ViewBinding
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.databinding.ActivityNameClanBinding
import com.example.toastgoand.home.LandingActivity
import com.example.toastgoand.home.startclan.network.StartClanApi
import com.example.toastgoand.home.startclan.network.StartClanDataClass
import com.example.toastgoand.uibits.HeaderOtherScreens
import kotlinx.coroutines.launch

class NameClanActivity : BaseActivity() {
    private lateinit var binding: ActivityNameClanBinding

    private lateinit var viewModel: NameClanViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = viewBinding as ActivityNameClanBinding


        fun onBackPressedHere() {
            onBackPressed()
        }

        setContent {
            AyeTheme() {

                val context = LocalContext.current

                val chosencontactssize = intent.getStringExtra("chosencontactssize")
                val chosencontacts = intent.getStringArrayExtra("chosencontacts")
                val userid = intent.getStringExtra("userid")

                val textState = remember { mutableStateOf(TextFieldValue()) }

                val showNameSetUp = remember { mutableStateOf(true) }

                var responseClanId = remember { mutableStateOf("") }

                val composableScope = rememberCoroutineScope()

                fun startClan() {
                    val name_here = textState.value.text
                    val admin_here = userid?.toInt()
                    val members_here = userid

                    val payload_here: StartClanDataClass =
                        StartClanDataClass(name_here, members_here, admin_here)

                    composableScope.launch {
                        try {
                            val responseStartClan =
                                StartClanApi.retrofitService.startClan(payload_here)

                            responseClanId.value = responseStartClan.toString()

                            if (chosencontacts != null) {
                                for (item in chosencontacts) {
                                    if (userid != null) {
                                        viewModel.invitePeopleToClub(
                                            phone = item, clubid = responseClanId.value,
                                            hostuserid = userid
                                        )
                                    }
                                }
                            }

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
                            title = "",
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
        return ActivityNameClanBinding.inflate(layoutInflater)
    }
}