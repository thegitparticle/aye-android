package com.example.toastgoand.home.clantalk

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.viewbinding.ViewBinding
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.databinding.ActivityClanTalkBinding
import com.example.toastgoand.dummy.DummyClanHub
import com.example.toastgoand.home.clanframes.ClanFramesActivity
import com.example.toastgoand.home.clanhub.components.UsersListItem
import com.example.toastgoand.home.clantalk.camera.CameraActivity
import com.example.toastgoand.uibits.HeaderPlayScreens
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.statusBarsPadding
import compose.icons.FeatherIcons
import compose.icons.feathericons.Camera
import compose.icons.feathericons.Layers
import compose.icons.feathericons.PlusSquare

class ClanTalkActivity : BaseActivity() {

    private lateinit var binding: ActivityClanTalkBinding

    private lateinit var viewModel: ClanTalkViewModel

    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = viewBinding as ActivityClanTalkBinding

        val allMessages = ""

        setContent {
            AyeTheme() {

                val members = DummyClanHub.clanHub.users

                val clubName = intent.getStringExtra("clubName")
                val clubid = intent.getIntExtra("clubid", 0)
                val channelid = intent.getStringExtra("channelid")
                val ongoingFrame = intent.getBooleanExtra("ongoingFrame", false)
                val startTime = intent.getStringExtra("startTime")
                val endTime = intent.getStringExtra("endTime")

                var showStartClanOverlay by remember { mutableStateOf(true) }

                if (ongoingFrame) {
                    showStartClanOverlay = false
                }

                val context = LocalContext.current

                ProvideWindowInsets {
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
                            AnimatedVisibility(visible = !showStartClanOverlay) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    FloatingActionButton(
                                        onClick = { context.startActivity(
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
                                            }) },
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
                                        onClick = { /* TODO */ },
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
                        },
                        floatingActionButtonPosition = FabPosition.Center,
                    ) {
                        LazyColumn(
                            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(),
                        ) {
                            item {
                                Spacer(modifier = Modifier.size(100.dp))
                            }
                            items(
                                items = members,
                                itemContent = {
                                    UsersListItem(user = it)
                                })
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