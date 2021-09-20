package com.example.toastgoand.home.clantalk.components

import android.util.Log
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.core.graphics.alpha
import com.example.toastgoand.home.clantalk.network.NewClanFrameApi
import com.example.toastgoand.home.clantalk.network.NewClanFrameDataClass
import com.ncorti.slidetoact.SlideToActView
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock


@Composable
fun StartClanFrame(
    modifier: Modifier,
    clubid: Int,
    channelid: String,
    changeLiveStatus: () -> Unit,
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

    fun startFrameHere(): Boolean {
        composableScope.launch {
            try {
                var responseStartFrame =
                    NewClanFrameApi.retrofitService.startNewClanFrame(newFrameInfo)
                changeLiveStatus()
                Log.i("startframeapicall", "it worked")
            } catch (e: Exception) {
                Log.i("startframeapicall", e.toString())
            }
        }
        return true
    }

    val context = LocalContext.current

    Surface(elevation = 5.dp) {
        Column(
            modifier = Modifier
                .fillMaxHeight(0.3f)
                .fillMaxWidth()
                .background(MaterialTheme.colors.onBackground),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight(0.3f)
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.onBackground),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "start frame to get talking",
                    style = MaterialTheme.typography.subtitle1,
                    color = MaterialTheme.colors.background,
                    modifier = Modifier.padding(vertical = 5.dp)
                )
                Text(
                    text = "a frame lasts 12 hours",
                    style = MaterialTheme.typography.caption,
                    color = MaterialTheme.colors.background,
                    modifier = Modifier.padding(vertical = 5.dp)
                )
            }

            val sliderColor = MaterialTheme.colors.primary
            val slideText = MaterialTheme.typography.subtitle2

            AndroidView(
                factory = { ctx ->
                    SlideToActView(ctx).apply {
                        layoutParams = LinearLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
                        innerColor = sliderColor.hashCode()
                        textAppearance = slideText.hashCode()
                        text = "slide to start frame"
                    }
                }
            )
        }
    }
}

