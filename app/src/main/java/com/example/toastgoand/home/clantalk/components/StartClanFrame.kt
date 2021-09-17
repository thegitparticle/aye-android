package com.example.toastgoand.home.clantalk.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.toastgoand.home.clantalk.network.NewClanFrameApi
import com.example.toastgoand.home.clantalk.network.NewClanFrameDataClass
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

@Composable
fun StartClanFrame(modifier: Modifier, clubid: Int, channelid: String, changeLiveStatus: () -> Unit) {
    val currentMoment: Long = Clock.System.now().epochSeconds

    Log.i("startframeapicall", currentMoment.toString())

    val newFrameInfo = NewClanFrameDataClass(
        start_time = currentMoment.toString(),
    end_time = (currentMoment + 43200).toString(),
        club_name = clubid,
        channel_id = channelid
    )

    val composableScope = rememberCoroutineScope()
    fun startFrameHere() {
        composableScope.launch {
            try {
                var responseStartFrame = NewClanFrameApi.retrofitService.startNewClanFrame(newFrameInfo)
                changeLiveStatus()
                Log.i ("startframeapicall", "it worked")
            } catch (e: Exception) {
                Log.i ("startframeapicall", e.toString())
            }
        }
    }

    Surface(elevation = 5.dp) {
        Column(modifier = Modifier
            .fillMaxHeight(0.3f)
            .fillMaxWidth()) {
            Text(
                text = "start frame to get talking",
                style = MaterialTheme.typography.subtitle1,
                color = MaterialTheme.colors.onBackground
            )
            Text(
                text = "a frame lasts 12 hours",
                style = MaterialTheme.typography.caption,
                color = MaterialTheme.colors.onBackground
            )
            Box(
                modifier = Modifier
                    .wrapContentWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colors.primary.copy(.08f))
                    .clickable { startFrameHere() }
            ) {
                Text(
                    text = "start frame", modifier = Modifier.padding(20.dp, 10.dp, 20.dp, 10.dp),
                    style = MaterialTheme.typography.caption,
                    color = MaterialTheme.colors.primary
                )
            }
        }
    }
}