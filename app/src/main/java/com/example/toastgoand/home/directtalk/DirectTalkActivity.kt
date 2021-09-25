package com.example.toastgoand.home.directtalk

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.viewbinding.ViewBinding
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.ToastgoApplication
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.databinding.ActivityDirectTalkBinding
import com.example.toastgoand.dummy.DummyClanHub
import com.example.toastgoand.home.clantalk.components.NewPNMessage
import com.example.toastgoand.home.clantalk.components.OldPNMessage
import com.example.toastgoand.home.directframes.DirectFramesActivity
import com.example.toastgoand.home.directtalk.camera.CameraDirectActivity
import com.example.toastgoand.home.directtalk.components.StartDirectFrame
import com.example.toastgoand.home.directtalk.components.TextInputDirect
import com.example.toastgoand.network.defaultrecos.DefaultRecosDataClass
import com.example.toastgoand.uibits.HeaderPlayScreens
import com.google.accompanist.insets.ExperimentalAnimatedInsets
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.rememberImeNestedScrollConnection
import com.google.accompanist.insets.statusBarsPadding
import com.pubnub.api.PNConfiguration
import com.pubnub.api.PubNub
import com.pubnub.api.enums.PNReconnectionPolicy
import com.pubnub.api.models.consumer.history.PNHistoryItemResult
import com.pubnub.api.models.consumer.pubsub.PNMessageResult
import compose.icons.FeatherIcons
import compose.icons.feathericons.Camera
import compose.icons.feathericons.Layers
import compose.icons.feathericons.PlusSquare
import kotlinx.datetime.Clock

class DirectTalkActivity : BaseActivity() {
    private lateinit var binding: ActivityDirectTalkBinding

    private val viewModel: DirectTalkViewModel by viewModels {
        DirectTalkViewModelFactory(
            (this.application as ToastgoApplication).repository,
            (this.application as ToastgoApplication).repositoryDefaultRecos
        )
    }


    @ExperimentalAnimationApi
    @ExperimentalAnimatedInsets
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = viewBinding as ActivityDirectTalkBinding

        val allMessages = ""

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            AyeTheme {

                val oldMessagesHere: List<PNHistoryItemResult> by viewModel.oldMessages.observeAsState(
                    listOf<PNHistoryItemResult>()
                )

                val newMessagesHere: List<PNMessageResult> by viewModel.newMessages.observeAsState(
                    listOf<PNMessageResult>()
                )

                Log.i("livemessage", newMessagesHere.toString())

                val defaultRecos: List<DefaultRecosDataClass> by viewModel.recos.observeAsState(
                    listOf<DefaultRecosDataClass>()
                )

                val otherName = intent.getStringExtra("otherName")
                val directid = intent.getStringExtra("directid")
                var ongoingFrame = intent.getBooleanExtra("ongoingFrame", false)
                val startTime = intent.getStringExtra("startTime")
                val endTime = intent.getStringExtra("endTime")

                val pnConfiguration = PNConfiguration().apply {
                    subscribeKey = "sub-c-d099e214-9bcf-11eb-9adf-f2e9c1644994"
                    publishKey = "pub-c-a65bb691-5b8a-4c4b-aef5-e2a26677122d"
                    secure = true
                    uuid = viewModel.deets.value?.user?.id.toString()
                    reconnectionPolicy = PNReconnectionPolicy.LINEAR
                }

                val pubNub = PubNub(pnConfiguration)

                pubNub.subscribe(
                    channels = listOf(directid) as List<String>
                )

                val currentMoment: Long = Clock.System.now().epochSeconds

                if (ongoingFrame) {
                    if (directid != null) {
                        if (startTime != null) {
                            viewModel.watchLiveMessages(
                                pubnub = pubNub,
                                channelid = directid,
                                start = currentMoment * 10000000,
                                end = startTime.toLong() * 10000000
                            )
                            viewModel.getOldMessages(
                                pubNub = pubNub,
                                channelid = directid,
                                start = currentMoment * 10000000,
                                end = startTime.toLong() * 10000000
                            )

                        }
                    }
                }

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

                fun changeFrameLiveStatus() {
                    ongoingFrame = true
                    Log.i("startframeapicall", "backwaeds function call worked")
                }

                ProvideWindowInsets(windowInsetsAnimationsEnabled = true) {
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
                                                putExtra(
                                                    "userid",
                                                    viewModel.deets.value?.user?.id.toString()
                                                )
                                                putExtra(
                                                    "userdp",
                                                    viewModel.deets.value?.image
                                                )
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
                                                        putExtra(
                                                            "userid",
                                                            viewModel.deets.value?.user?.id.toString()
                                                        )
                                                        putExtra(
                                                            "userdp",
                                                            viewModel.deets.value?.image
                                                        )
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
                            if (ongoingFrame) {
                                AnimatedVisibility(visible = showTextInputDirect) {
                                    if (directid != null) {
                                        if (otherName != null) {
                                            TextInputDirect(
                                                userid = viewModel.deets.value?.user?.id.toString(),
                                                channelid = directid,
                                                defaultRecos = defaultRecos,
                                                modifier = Modifier,
                                                otherName = otherName,
                                                myName = viewModel.deets.value?.user?.full_name.toString()
                                            )
                                        }
                                    }
                                }
                            } else {
                                if (directid != null) {
                                    StartDirectFrame(
                                        modifier = Modifier,
                                        directid = directid,
                                        changeLiveStatus = ::changeFrameLiveStatus,
                                        pubNub = pubNub,
                                        myName = viewModel.deets.value?.user?.full_name.toString()
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
                                    .background(AyeTheme.colors.uiBackground)
                            ) {
                                item {
                                    Spacer(modifier = Modifier.size(100.dp))
                                }
                                if (oldMessagesHere.isNotEmpty()) {
                                    items(
                                        items = oldMessagesHere,
                                        itemContent = {
                                            if (directid != null) {
                                                OldPNMessage(
                                                    message = it,
                                                    userid = viewModel.deets.value?.user?.id.toString(),
                                                    channelid = directid
                                                )
                                            }
                                        })
                                    items(
                                        items = newMessagesHere,
                                        itemContent = {
                                            if (directid != null) {
                                                NewPNMessage(
                                                    message = it,
                                                    userid = viewModel.deets.value?.user?.id.toString(),
                                                    channelid = directid
                                                )
                                                Log.i("livemessage", "new pn message called")
                                            }
                                        })
                                }
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