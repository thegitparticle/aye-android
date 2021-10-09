package com.example.toastgoand.home.clantalk.components

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.ui.res.imageResource
import androidx.ui.res.loadImageResource
import coil.ImageLoader
import coil.compose.rememberImagePainter
import coil.decode.VideoFrameDecoder
import coil.fetch.VideoFrameFileFetcher
import coil.fetch.VideoFrameUriFetcher
import com.beust.klaxon.Parser
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.uibits.ComposeExoPlayer
import com.example.toastgoand.uibits.ViewMediaActivity
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.google.gson.Gson
import com.pubnub.api.PNConfiguration
import com.pubnub.api.PubNub
import com.pubnub.api.models.consumer.history.PNHistoryItemResult
import kotlinx.serialization.Serializable
import com.google.gson.reflect.TypeToken
import com.squareup.moshi.*
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import org.json.JSONObject
import java.io.File
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType


@Serializable
data class MessageMetaData(val image_url: String, val user_dp: String, val type: String)

@Serializable
@JsonClass(generateAdapter = true)
data class CEntryData(val message: String, val file: CEntryFile)

@Serializable
@JsonClass(generateAdapter = true)
data class CEntryFile(val id: String, val name: String)

@Composable
fun OldPNMessage(
    message: PNHistoryItemResult,
    userid: String,
    channelid: String,
    visibleItems: Int,
    thisItemIndex: Int
) {

    AyeTheme() {

        val metaData = Gson().fromJson<MessageMetaData>(message.meta, MessageMetaData::class.java)

        if (thisItemIndex === visibleItems) {
            Log.i("scrollplaydebug", visibleItems.toString())
        }

        if (metaData.type == "h") {
            HMessage(message = message)
//            Log.i("cmessagedebugmain", "normal messages log")
        } else if (metaData.type == "c") {
//            Log.i("cmessagedebugmain", "else if log before component invoke")
            CMessage(message = message, userid = userid, channelid = channelid)
        } else if (metaData.type == "s") {
            SMessage(message = message, thisItemIndex = thisItemIndex, visibleItems = visibleItems)
        }
    }

}

@Composable
private fun SMessage(message: PNHistoryItemResult, thisItemIndex: Int, visibleItems: Int) {
    AyeTheme() {

        val metaData = Gson().fromJson<MessageMetaData>(message.meta, MessageMetaData::class.java)

        Log.i("pubnub - only link", metaData.image_url)

        Box(
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .height(250.dp)
                .padding(vertical = 5.dp)
                .clip(RoundedCornerShape(corner = CornerSize(15.dp))),
            contentAlignment = Alignment.Center
        ) {
            ComposeExoPlayer(
                videoLink = metaData.image_url,
//                thumbNail = "https://i.postimg.cc/y6mnnk9H/download-36.jpg".toUri(),
                thumbNail = metaData.image_url.toUri(),
                isPlaying = (thisItemIndex == visibleItems)
            )
            DPBubble(dplink = metaData.user_dp, text = message.entry.toString())
        }
    }
}

@Composable
private fun CMessage(message: PNHistoryItemResult, userid: String, channelid: String) {
    AyeTheme() {

        var urlOfFileHere by remember { mutableStateOf("") }
        var imageLinkFound by remember {
            mutableStateOf(false)
        }

        val composableScope = rememberCoroutineScope()

        val metaData = Gson().fromJson<MessageMetaData>(message.meta, MessageMetaData::class.java)

        val json = JSONObject(message.entry.toString())
        val message_text = json.getString("message")
        val file = json.getJSONObject("file")
        val json2 = JSONObject(file.toString())
        val file_id = json2.getString("id")
        val file_name = json2.getString("name")

        val pnConfiguration = PNConfiguration().apply {
            subscribeKey = "sub-c-d099e214-9bcf-11eb-9adf-f2e9c1644994"
            publishKey = "pub-c-a65bb691-5b8a-4c4b-aef5-e2a26677122d"
            secure = true
            uuid = userid
        }

        val pubnub = PubNub(pnConfiguration)

        urlOfFileHere = GetFileLinkFromPN(
            userid = userid,
            channelid = channelid,
            filename = file_name,
            fileid = file_id
        )


        if (urlOfFileHere.isNotEmpty()) {
            if (urlOfFileHere == "error") {
                Log.i("cmessagedebugmain", "file link is error")
                Log.i("cmessagedebugmainurlerror", urlOfFileHere)
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.95f)
                        .height(250.dp)
                        .padding(vertical = 5.dp)
                        .clip(RoundedCornerShape(corner = CornerSize(15.dp))),
                    contentAlignment = Alignment.Center
                ) {
                    DPBubble(dplink = metaData.user_dp, text = message_text.toString())
                }
            } else {
                Log.i("cmessagedebugmain", "file link is right url string")
                Log.i("cmessagedebugmainurlright", urlOfFileHere)
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.95f)
                        .height(250.dp)
                        .padding(vertical = 5.dp)
                        .clip(RoundedCornerShape(corner = CornerSize(15.dp))),
                    contentAlignment = Alignment.Center
                ) {
                    ImageHereForC(imageLink = urlOfFileHere)
                    DPBubble(dplink = metaData.user_dp, text = message_text.toString())
                }
            }
        } else {
            Log.i("cmessagedebugmain", "file link not avaialble yet")
            Log.i("cmessagedebugmainurlnot", urlOfFileHere)
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.95f)
                    .height(250.dp)
                    .padding(vertical = 5.dp)
                    .clip(RoundedCornerShape(corner = CornerSize(15.dp))),
                contentAlignment = Alignment.Center
            ) {
                DPBubble(dplink = metaData.user_dp, text = message_text.toString())
            }
        }

    }
}

@Composable
private fun HMessage(message: PNHistoryItemResult) {
    AyeTheme() {

        val metaData = Gson().fromJson<MessageMetaData>(message.meta, MessageMetaData::class.java)

        Log.i("pubnub - only link", metaData.image_url)

        Box(
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .height(250.dp)
                .padding(vertical = 5.dp)
                .clip(RoundedCornerShape(corner = CornerSize(15.dp))),
            contentAlignment = Alignment.Center
        ) {
            ImageHere(imageLink = metaData.image_url)
            DPBubble(dplink = metaData.user_dp, text = message.entry.toString())
        }
    }
}

@Composable
private fun ImageHere(imageLink: String) {
    val painter = rememberImagePainter(data = imageLink)
    val context = LocalContext.current

    Image(
        painter = painter,
        contentDescription = "message image link",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(250.dp)
            .clip(RoundedCornerShape(corner = CornerSize(15.dp)))
            .clickable {
                context.startActivity(
                    Intent(
                        context,
                        ViewMediaActivity::class.java
                    ).apply {
                        putExtra("imagelink", imageLink)
                    })
            }
    )
}

@Composable
private fun ImageHereForC(imageLink: String) {
    val painter = rememberImagePainter(data = imageLink.toUri())
    val context = LocalContext.current
    Log.i("cmessagedebug inside painter", imageLink)
    Log.i("cmessagedebug inside painter - paunter log", painter.toString())

    Image(
        painter = painter,
        contentDescription = "message image link",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(250.dp)
            .clip(RoundedCornerShape(corner = CornerSize(15.dp)))
            .clickable {
                context.startActivity(
                    Intent(
                        context,
                        ViewMediaActivity::class.java
                    ).apply {
                        putExtra("imagelink", imageLink)
                    })
            }
    )
}


@Composable
private fun DPBubble(dplink: String, text: String) {
    Column() {
        TextBubble(text = text)
        DPHere(dplink = dplink)
    }
}

@Composable
private fun DPHere(dplink: String) {
    val painter = rememberImagePainter(data = dplink)
    Image(
        painter = painter,
        contentDescription = "message dp link",
        modifier = Modifier
            .padding(8.dp)
            .size(50.dp)
            .clip(RoundedCornerShape(corner = CornerSize(25.dp)))
    )
}

private val ChatBubbleShape = RoundedCornerShape(0.dp, 8.dp, 8.dp, 0.dp)

@Composable
private fun TextBubble(text: String) {
    Box(
        modifier = Modifier
            .clip(ChatBubbleShape)
            .background(AyeTheme.colors.uiSurface.copy(0.75f))
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.body2,
            color = AyeTheme.colors.textSecondary,
            modifier = Modifier.padding(
                horizontal = 20.dp, vertical = 10.dp
            )
        )
    }
}