package com.example.toastgoand.home.clantalk

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
import com.example.toastgoand.databinding.ActivityClanTalkBinding
import com.example.toastgoand.home.clanframes.ClanFramesActivity
import com.example.toastgoand.home.clantalk.camera.CameraActivity
import com.example.toastgoand.home.clantalk.components.NewPNMessage
import com.example.toastgoand.home.clantalk.components.OldPNMessage
import com.example.toastgoand.home.clantalk.components.StartClanFrame
import com.example.toastgoand.home.clantalk.components.TextInputPart
import com.example.toastgoand.network.defaultrecos.DefaultRecosDataClass
import com.example.toastgoand.quick.QuickActivity
import com.example.toastgoand.uibits.HeaderPlayScreens
import com.google.accompanist.insets.ExperimentalAnimatedInsets
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.rememberImeNestedScrollConnection
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.insets.ui.Scaffold
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

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            AyeTheme {

                val oldMessagesHere: List<PNHistoryItemResult> by viewModel.oldMessages.observeAsState(
                    listOf<PNHistoryItemResult>()
                )

                val newMessagesHere: List<PNMessageResult> by viewModel.newMessages.observeAsState(
                    listOf<PNMessageResult>()
                )

                Log.i("livemessage in activity", newMessagesHere.toString())

                val defaultRecos: List<DefaultRecosDataClass> by viewModel.recos.observeAsState(
                    listOf<DefaultRecosDataClass>()
                )

                val clubName = intent.getStringExtra("clubName")
                val clubid = intent.getIntExtra("clubid", 0)
                val channelid = intent.getStringExtra("channelid")
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
                    channels = listOf(channelid) as List<String>
                )

                val currentMoment: Long = Clock.System.now().epochSeconds

                if (ongoingFrame) {
                    if (channelid != null) {
                        if (startTime != null) {
                            viewModel.watchLiveMessages(
                                pubnub = pubNub,
                                channelid = channelid,
                                start = currentMoment * 10000000,
                                end = startTime.toLong() * 10000000
                            )
                            viewModel.getOldMessages(
                                pubNub = pubNub,
                                channelid = channelid,
                                start = currentMoment * 10000000,
                                end = startTime.toLong() * 10000000
                            )

                        }
                    }
                }


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

                Log.i("recos", defaultRecos.toString())

                ProvideWindowInsets(windowInsetsAnimationsEnabled = true) {
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
                            AnimatedVisibility(visible = ongoingFrame) {
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
                                                            putExtra(
                                                                "userid",
                                                                viewModel.deets.value?.user?.id.toString()
                                                            )
                                                        })
                                                },
                                                modifier = Modifier
                                                    .padding(horizontal = 25.dp)
                                                    .size(40.dp),
                                                backgroundColor = AyeTheme.colors.textSecondary,
                                            ) {
                                                Icon(
                                                    imageVector = FeatherIcons.Camera,
                                                    contentDescription = "last month",
                                                    modifier = Modifier.size(20.dp)
                                                )
                                            }
                                            FloatingActionButton(
                                                onClick = {
                                                    context.startActivity(
                                                        Intent(
                                                            context,
                                                            QuickActivity::class.java
                                                        ).apply { })
                                                },
                                                modifier = Modifier
                                                    .padding(horizontal = 25.dp)
                                                    .size(60.dp),
                                                backgroundColor = AyeTheme.colors.success,
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
                                                backgroundColor = AyeTheme.colors.appLead,
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
                            }
                        },
                        floatingActionButtonPosition = FabPosition.Center,
                        bottomBar = {
                            if (ongoingFrame) {
                                AnimatedVisibility(visible = showTextInput) {
                                    if (channelid != null) {
                                        if (clubName != null) {
                                            TextInputPart(
                                                userid = viewModel.deets.value?.user?.id.toString(),
                                                channelid = channelid,
                                                defaultRecos = defaultRecos,
                                                clubName = clubName
                                            )
                                        }
                                    }
                                }
                            } else {
                                if (channelid != null) {
                                    if (clubName != null) {
                                        StartClanFrame(
                                            modifier = Modifier,
                                            clubid = clubid,
                                            channelid = channelid,
                                            changeLiveStatus = ::changeFrameLiveStatus,
                                            pubNub = pubNub,
                                            clubName = clubName
                                        )
                                    }
                                }
                            }

                        },
                        modifier = Modifier.background(AyeTheme.colors.uiBackground)
                    ) { contentPadding ->
                        Column {
                            LazyColumn(
                                contentPadding = PaddingValues(vertical = 8.dp),
                                reverseLayout = true,
                                modifier = Modifier
                                    .weight(1f)
                                    .nestedScroll(connection = rememberImeNestedScrollConnection())
                                    .clickable { reSetTextInput() }
                                    .fillMaxWidth()
                                    .background(AyeTheme.colors.uiBackground),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                item {
                                    Spacer(modifier = Modifier.size(100.dp))
                                }
                                if (oldMessagesHere.isNotEmpty()) {
                                    items(
                                        items = oldMessagesHere,
                                        itemContent = {
                                            if (channelid != null) {
                                                OldPNMessage(
                                                    message = it,
                                                    userid = viewModel.deets.value?.user?.id.toString(),
                                                    channelid = channelid
                                                )
                                            }
                                        })
                                    items(
                                        items = newMessagesHere,
                                        itemContent = {
                                            if (channelid != null) {
                                                NewPNMessage(
                                                    message = it,
                                                    userid = viewModel.deets.value?.user?.id.toString(),
                                                    channelid = channelid
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
        return ActivityClanTalkBinding.inflate(layoutInflater)
    }
}