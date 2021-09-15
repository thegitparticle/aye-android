package com.example.toastgoand.home.clantalk

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import com.google.accompanist.insets.ui.Scaffold
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.viewbinding.ViewBinding
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.databinding.ActivityClanTalkBinding
import com.example.toastgoand.dummy.DummyClanHub
import com.example.toastgoand.home.LandingActivity
import com.example.toastgoand.home.clanframes.ClanFramesActivity
import com.example.toastgoand.home.clanhub.components.UsersListItem
import com.example.toastgoand.uibits.HeaderPlayScreens
import com.example.toastgoand.uibits.TopHeaderPlayScreens
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsHeight
import com.google.accompanist.insets.statusBarsPadding
import compose.icons.FeatherIcons
import compose.icons.feathericons.Layers

class ClanTalkActivity : BaseActivity() {

    private lateinit var binding: ActivityClanTalkBinding

    private lateinit var viewModel: ClanTalkViewModel

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

                    val context = LocalContext.current

                ProvideWindowInsets {
                    Scaffold(
                        topBar = {
                            if (clubName != null) {
                                HeaderPlayScreens(
                                    modifier = Modifier.statusBarsPadding(),
                                    title = clubName,
                                    onBackIconPressed = {
                                        context.startActivity(
                                            Intent(
                                                context,
                                                LandingActivity::class.java
                                            )
                                        )
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
                            FloatingActionButton(onClick = { /* TODO */ }) {
                                Icon(
                                    imageVector = FeatherIcons.Layers,
                                    contentDescription = "last month",
                                )
                            }
                        }
                    ) {
                        LazyColumn(
                            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                            modifier = Modifier
                                .fillMaxWidth().fillMaxHeight(),
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