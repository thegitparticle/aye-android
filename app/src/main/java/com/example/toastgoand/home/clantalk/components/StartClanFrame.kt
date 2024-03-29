package com.example.toastgoand.home.clantalk.components

import android.util.Log
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.home.clantalk.network.NewClanFrameApi
import com.example.toastgoand.home.clantalk.network.NewClanFrameDataClass
import com.example.toastgoand.home.notifications.ChannelData
import com.example.toastgoand.home.notifications.NotificationData
import com.example.toastgoand.home.notifications.Payload
import com.example.toastgoand.home.notifications.StartFrameNotifPayloadDataClass
import com.ncorti.slidetoact.SlideToActView
import com.pubnub.api.PubNub
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock


@Composable
fun StartClanFrame(
    modifier: Modifier,
    clubid: Int,
    channelid: String,
    changeLiveStatus: () -> Unit,
    pubNub: PubNub,
    clubName: String
) {
    val currentMoment: Long = Clock.System.now().epochSeconds

    Log.i("startframeapicall", currentMoment.toString())

    val newFrameInfo = NewClanFrameDataClass(
        start_time = currentMoment.toString(),
        end_time = (currentMoment + 43200).toString(),
        club_name = clubid,
        channel_id = channelid
    )

    val composableScope = rememberCoroutineScope()

    val listener: ((SlideToActView.OnSlideCompleteListener))? = null

    val channel1: ChannelData = ChannelData(channel = channelid)
    val notification1: NotificationData =
        NotificationData(title = clubName, body = "new frame started", sound = "default")
    val p1: Payload = Payload(data = channel1, notification = notification1)

    val payloadHere: StartFrameNotifPayloadDataClass = StartFrameNotifPayloadDataClass(pc_gcm = p1)

    fun startFrameHere() {
        Log.i("startframeapicall", "start frame called")
        composableScope.launch {
            Log.i("startframeapicall", "inside composable Scope")
            try {
                var responseStartFrame =
                    NewClanFrameApi.retrofitService.startNewClanFrame(newFrameInfo)
                changeLiveStatus()
                pubNub.publish(message = payloadHere, channel = channelid + "_push")
                    .async { result, status ->
                        if (!status.error) {
                            Log.i("startframeapicall notif", "notif it worked")
                        } else {
                            Log.i("startframeapicall notif", status.toString())
                        }
                    }
                Log.i("startframeapicall", "it worked")
            } catch (e: Exception) {
                Log.i("startframeapicall", e.toString())
            }
        }
    }

    val context = LocalContext.current

    Surface(elevation = 5.dp) {
        Column(
            modifier = Modifier
                .fillMaxHeight(0.3f)
                .fillMaxWidth()
                .background(AyeTheme.colors.textSecondary),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight(0.3f)
                    .fillMaxWidth()
                    .background(AyeTheme.colors.textSecondary),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "start frame to get talking",
                    style = MaterialTheme.typography.subtitle1,
                    color = AyeTheme.colors.uiBackground,
                    modifier = Modifier.padding(vertical = 5.dp)
                )
                Text(
                    text = "a frame lasts 12 hours",
                    style = MaterialTheme.typography.caption,
                    color = AyeTheme.colors.uiSurface.copy(0.75f),
                    modifier = Modifier.padding(vertical = 5.dp)
                )
            }

            val sliderColor = AyeTheme.colors.uiBackground
            var sliderOuterColor =  AyeTheme.colors.appLead
            val slideText = MaterialTheme.typography.subtitle2

            AndroidView(
                factory = { ctx ->
                    SlideToActView(ctx).apply {
                        layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
                        outerColor = sliderOuterColor.hashCode()
                        innerColor = sliderColor.hashCode()
                        textAppearance = slideText.hashCode()
                        text = "slide to start frame"
                        setOnClickListener {
                            startFrameHere()
                        }
                    }
                }
            )
        }
    }
}

