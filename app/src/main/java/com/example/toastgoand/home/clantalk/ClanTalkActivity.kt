package com.example.toastgoand.home.clantalk

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.google.accompanist.insets.ui.Scaffold
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.viewbinding.ViewBinding
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.ToastgoApplication
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.databinding.ActivityClanTalkBinding
import com.example.toastgoand.dummy.DummyClanHub
import com.example.toastgoand.home.clanframes.ClanFramesActivity
import com.example.toastgoand.home.clanhub.components.UsersListItem
import com.example.toastgoand.home.clantalk.camera.CameraActivity
import com.example.toastgoand.home.clantalk.components.StartClanFrame
import com.example.toastgoand.home.clantalk.components.TextInputPart
import com.example.toastgoand.uibits.HeaderPlayScreens
import com.google.accompanist.insets.*
import compose.icons.FeatherIcons
import compose.icons.feathericons.Camera
import compose.icons.feathericons.Layers
import compose.icons.feathericons.PlusSquare

class ClanTalkActivity : BaseActivity() {

    private lateinit var binding: ActivityClanTalkBinding

    private val viewModel: ClanTalkViewModel by viewModels {
        ClanTalkViewModelFactory(
            (this.application as ToastgoApplication).repository,
            (this.application as ToastgoApplication).repositoryDefaultRecos
        )
    }

    @ExperimentalAnimatedInsets
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = viewBinding as ActivityClanTalkBinding

        val allMessages = ""

//        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            AyeTheme() {

                val members = DummyClanHub.clanHub.users

                val clubName = intent.getStringExtra("clubName")
                val clubid = intent.getIntExtra("clubid", 0)
                val channelid = intent.getStringExtra("channelid")
                var ongoingFrame = intent.getBooleanExtra("ongoingFrame", false)
                val startTime = intent.getStringExtra("startTime")
                val endTime = intent.getStringExtra("endTime")


                var showTextInput by remember { mutableStateOf(false) }
                val focusTextInputRequester = remember { FocusRequester() }



                fun setUpTextInput() {
                    showTextInput = true
//                    focusTextInputRequester.requestFocus()
                }


                fun reSetTextInput() {
                    showTextInput = false
                }

                fun changeFrameLiveStatus() {
                    ongoingFrame = true
                    Log.i("startframeapicall", "backwaeds function call worked")
                }

                val context = LocalContext.current

                ProvideWindowInsets() {
                    Scaffold(
                        topBar = {
                            if (clubName != null) {
                                HeaderPlayScreens(
                                    modifier = Modifier.statusBarsPadding(),
                                    title = clubName,
                                    onBackIconPressed = {
                                        onBackPressed()
                                    },
                                    onActionIconPressed = {
                                        context.startActivity(
                                            Intent(
                                                context,
                                                ClanFramesActivity::class.java
                                            ).apply {
                                                putExtra("clubName", clubName)
                                                putExtra("clubid", clubid)
                                                putExtra("channelid", channelid)
                                                putExtra("ongoingFrame", ongoingFrame)
                                                putExtra("startTime", startTime)
                                                putExtra("endTime", endTime)
                                            })
                                    },
                                    actionIcon = FeatherIcons.Layers
                                )
                            }
                        },
                        floatingActionButton = {
//                            AnimatedVisibility(visible = !showStartClanOverlay) {
//                                AnimatedVisibility(visible = !showTextInput) {
                            AnimatedVisibility(visible = true) {
                                AnimatedVisibility(visible = !showTextInput) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.Center,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        FloatingActionButton(
                                            onClick = {
                                                context.startActivity(
                                                    Intent(
                                                        context,
                                                        CameraActivity::class.java
                                                    ).apply {
                                                        putExtra("clubName", clubName)
                                                        putExtra("clubid", clubid)
                                                        putExtra("channelid", channelid)
                                                        putExtra("ongoingFrame", ongoingFrame)
                                                        putExtra("startTime", startTime)
                                                        putExtra("endTime", endTime)
                                                    })
                                            },
                                            modifier = Modifier
                                                .padding(horizontal = 25.dp)
                                                .size(40.dp),
                                            backgroundColor = MaterialTheme.colors.onBackground,
                                        ) {
                                            Icon(
                                                imageVector = FeatherIcons.Camera,
                                                contentDescription = "last month",
                                                modifier = Modifier.size(20.dp)
                                            )
                                        }
                                        FloatingActionButton(
                                            onClick = { /* TODO */ },
                                            modifier = Modifier
                                                .padding(horizontal = 25.dp)
                                                .size(60.dp),
                                            backgroundColor = MaterialTheme.colors.onSurface,
                                        ) {
                                            Icon(
                                                imageVector = FeatherIcons.PlusSquare,
                                                contentDescription = "last month",
                                                modifier = Modifier.size(30.dp)
                                            )
                                        }
                                        FloatingActionButton(
                                            onClick = { setUpTextInput() },
                                            modifier = Modifier
                                                .padding(horizontal = 25.dp)
                                                .size(40.dp),
                                            backgroundColor = MaterialTheme.colors.secondary,
                                        ) {
                                            Icon(
                                                imageVector = FeatherIcons.Layers,
                                                contentDescription = "last month",
                                                modifier = Modifier.size(20.dp)
                                            )
                                        }
                                    }
                                }
                            }
                        },
                        floatingActionButtonPosition = FabPosition.Center,
                        bottomBar = {
                            if (ongoingFrame) {
                                AnimatedVisibility(visible = showTextInput) {
                                        if (channelid != null) {
//                                            viewModel.recos.value?.let {
                                                TextInputPart(
                                                    userid = viewModel.deets.value?.user?.id.toString(),
                                                    channelid = channelid,
//                                                    defaultRecos = it
                                                )
//                                            }
                                    }
                                }
                            } else {
                                if (channelid != null) {
                                    StartClanFrame(
                                        modifier = Modifier,
                                        clubid = clubid,
                                        channelid = channelid,
                                        changeLiveStatus = ::changeFrameLiveStatus
                                    )
                                }
                            }

                        }
                    ) { contentPadding ->
                        Column {
                            LazyColumn(
                                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                                reverseLayout = true,
                                modifier = Modifier
                                    .weight(1f)
                                    .nestedScroll(connection = rememberImeNestedScrollConnection())
                                    .clickable { reSetTextInput() }
                                    .fillMaxWidth()
                            ) {
                                item {
                                    Spacer(modifier = Modifier.size(100.dp))
                                }
                                items(
                                    items = members,
                                    itemContent = {
                                        UsersListItem(user = it)
                                    })
                                item {
                                    Spacer(modifier = Modifier.size(100.dp))
                                }
                            }
                        }
                    }
                }
            }

        }
    }

    override fun binding(): ViewBinding {
        return ActivityClanTalkBinding.inflate(layoutInflater)
    }
}