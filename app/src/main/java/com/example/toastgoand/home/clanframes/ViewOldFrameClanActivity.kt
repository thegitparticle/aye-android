package com.example.toastgoand.home.clanframes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.R
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.databinding.ActivityClanFramesBinding
import com.example.toastgoand.databinding.ActivityViewOldFrameBinding
import com.example.toastgoand.databinding.ActivityViewOldFrameClanBinding
import com.example.toastgoand.home.clantalk.components.OldPNMessage
import com.example.toastgoand.network.pnstuff.pubNub
import com.example.toastgoand.uibits.HeaderOtherScreens
import com.example.toastgoand.uibits.HeaderPlayScreens
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.rememberImeNestedScrollConnection
import com.google.accompanist.insets.statusBarsPadding
import com.google.accompanist.insets.ui.Scaffold
import com.pubnub.api.PNConfiguration
import com.pubnub.api.PubNub
import com.pubnub.api.models.consumer.history.PNHistoryItemResult
import compose.icons.FeatherIcons
import compose.icons.feathericons.Layers

class ViewOldFrameClanActivity : BaseActivity() {

    private lateinit var binding: ActivityViewOldFrameClanBinding

    private lateinit var viewModel: ViewOldFrameClanViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = viewBinding as ActivityViewOldFrameClanBinding

        viewModel = ViewModelProvider(this).get(ViewOldFrameClanViewModel::class.java)

        fun onBackPressedHere() {
            onBackPressed()
        }

        setContent {
            AyeTheme() {
                val oldMessagesHere: List<PNHistoryItemResult> by viewModel.oldMessages.observeAsState(
                    listOf<PNHistoryItemResult>()
                )

                Log.i("oldframeabab messages in activity", oldMessagesHere.toString())

                val clubName = intent.getStringExtra("clubName")
                val channelid = intent.getStringExtra("channelid")
                val startTime = intent.getStringExtra("startTime")
                val endTime = intent.getStringExtra("endTime")
                val userid = intent.getStringExtra("userid")

                val context = LocalContext.current

                val pnConfiguration = PNConfiguration().apply {
                    subscribeKey = "sub-c-d099e214-9bcf-11eb-9adf-f2e9c1644994"
                    publishKey = "pub-c-a65bb691-5b8a-4c4b-aef5-e2a26677122d"
                    secure = true
                    if (userid != null) {
                        uuid = userid
                    }
                }

                val pubNub = PubNub(pnConfiguration)


                if (channelid != null) {
                    if (startTime != null) {
                        if (endTime != null) {
                            viewModel.getOldMessages(
                                pubNub = pubNub,
                                channelid = channelid,
                                start = startTime.toLong() * 10000000,
                                end = endTime.toLong() * 10000000
                            )
                        }
                    }
                }

                ProvideWindowInsets() {
                    Scaffold(
                        topBar = {
                            if (clubName != null) {
                                HeaderOtherScreens(
                                    modifier = Modifier.fillMaxWidth(),
                                    title = clubName,
                                    onBackIconPressed = { onBackPressedHere() }
                                )
                            }
                        },
                    ) { contentPadding ->
                        LazyColumn(
                            contentPadding = PaddingValues(vertical = 8.dp),
                            reverseLayout = true,
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            item {
                                Spacer(modifier = Modifier.size(100.dp))
                            }
                            if (oldMessagesHere.isNotEmpty()) {
                                items(
                                    items = oldMessagesHere,
                                    itemContent = {
                                        if (userid != null) {
                                            if (channelid != null) {
                                                OldPNMessage(
                                                    message = it,
                                                    userid = userid,
                                                    channelid = channelid
                                                )
                                            }
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

    override fun binding(): ViewBinding {
        return ActivityViewOldFrameClanBinding.inflate(layoutInflater)
    }

}