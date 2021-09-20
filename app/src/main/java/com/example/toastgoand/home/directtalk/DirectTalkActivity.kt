package com.example.toastgoand.home.directtalk

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.viewbinding.ViewBinding
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.databinding.ActivityClanTalkBinding
import com.example.toastgoand.databinding.ActivityDirectTalkBinding
import com.example.toastgoand.dummy.DummyClanHub
import com.example.toastgoand.home.clanframes.ClanFramesActivity
import com.example.toastgoand.home.clanhub.components.ClanMetrics
import com.example.toastgoand.home.clanhub.components.UsersListItem
import com.example.toastgoand.home.clantalk.ClanTalkViewModel
import com.example.toastgoand.home.clantalk.camera.CameraActivity
import com.example.toastgoand.home.clantalk.components.TextInputPart
import com.example.toastgoand.home.directframes.DirectFramesActivity
import com.example.toastgoand.home.directtalk.camera.CameraDirectActivity
import com.example.toastgoand.home.directtalk.components.TextInputDirect
import com.example.toastgoand.uibits.HeaderPlayScreens
import com.example.toastgoand.uibits.TopHeaderPlayScreens
import com.google.accompanist.insets.*
import compose.icons.FeatherIcons
import compose.icons.feathericons.Camera
import compose.icons.feathericons.Layers
import compose.icons.feathericons.PlusSquare

class DirectTalkActivity : BaseActivity() {
    private lateinit var binding: ActivityDirectTalkBinding

    private lateinit var viewModel: DirectTalkViewModel

    @ExperimentalAnimationApi
    @ExperimentalAnimatedInsets
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = viewBinding as ActivityDirectTalkBinding

        val allMessages = ""

        setContent {
            AyeTheme {
                val otherName = intent.getStringExtra("otherName")
                val directid = intent.getStringExtra("directid")
                val ongoingFrame = intent.getBooleanExtra("ongoingFrame", false)
                val startTime = intent.getStringExtra("startTime")
                val endTime = intent.getStringExtra("endTime")

                val members = DummyClanHub.clanHub.users
                val context = LocalContext.current

                var showStartClanOverlayDirect by remember { mutableStateOf(true) }
                var showTextInputDirect by remember { mutableStateOf(false) }
                val focusTextInputRequester = remember { FocusRequester() }

                Log.i("ongoing", ongoingFrame.toString())

                if (ongoingFrame) {
                    showStartClanOverlayDirect = false
                }

                fun setUpTextInputDirect() {
                    showTextInputDirect = true
//                    focusTextInputRequester.requestFocus()
                }

                fun reSetTextInputDirect() {
                    showTextInputDirect = false
                }

                ProvideWindowInsets() {
                    com.google.accompanist.insets.ui.Scaffold(
                        topBar = {
                            if (otherName != null) {
                                HeaderPlayScreens(
                                    modifier = Modifier.statusBarsPadding(),
                                    title = otherName,
                                    onBackIconPressed = {
                                        onBackPressed()
                                    },
                                    onActionIconPressed = {
                                        context.startActivity(
                                            Intent(
                                                context,
                                                DirectFramesActivity::class.java
                                            ).apply {
                                                putExtra("otherName", otherName)
                                                putExtra("directid", directid)
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
                            AnimatedVisibility(visible = !showStartClanOverlayDirect) {
                                AnimatedVisibility(visible = !showTextInputDirect) {
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
                                                        CameraDirectActivity::class.java
                                                    ).apply {
                                                        putExtra("otherName", otherName)
                                                        putExtra("directid", directid)
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
                                            onClick = { setUpTextInputDirect() },
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
                            AnimatedVisibility(visible = showTextInputDirect) {
                                Surface(elevation = 1.dp) {
                                    TextInputDirect(
                                        modifier = Modifier
                                            .focusRequester(
                                                focusTextInputRequester
                                            )
                                            .navigationBarsWithImePadding()
                                    )
                                }
                            }
                        }
                    ) {
                        Column {
                            LazyColumn(
                                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                                reverseLayout = true,
                                modifier = Modifier
                                    .weight(1f)
                                    .nestedScroll(connection = rememberImeNestedScrollConnection())
                                    .clickable { reSetTextInputDirect() }
                                    .fillMaxWidth()
                            ) {
                                item {
                                    Spacer(modifier = Modifier.size(100.dp))
                                }
                                items(
                                    items = members,
                                    itemContent = {
//                                        UsersListItem(user = it)
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
        return ActivityDirectTalkBinding.inflate(layoutInflater)
    }
}