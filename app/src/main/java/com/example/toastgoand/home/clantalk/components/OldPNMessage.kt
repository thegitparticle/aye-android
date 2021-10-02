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
import coil.compose.rememberImagePainter
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.uibits.ViewMediaActivity
import com.google.gson.Gson
import com.pubnub.api.PNConfiguration
import com.pubnub.api.PubNub
import com.pubnub.api.models.consumer.history.PNHistoryItemResult
import kotlinx.serialization.Serializable

@Serializable
data class MessageMetaData(val image_url: String, val user_dp: String, val type: String)

@Serializable
data class CEntryData(val message: String, val file: CEntryFile)

@Serializable
data class CEntryFile(val id: String, val name: String)

@Composable
fun OldPNMessage (message: PNHistoryItemResult, userid: String, channelid: String) {

    AyeTheme() {

        val metaData = Gson().fromJson<MessageMetaData>(message.meta, MessageMetaData::class.java)


        if (metaData.type == "h") {
            HMessage(message = message)
            Log.i("cmessagedebugmain", "normal messages log")
        } else if (metaData.type == "c") {
            Log.i("cmessagedebugmain", "else if log before component invoke")
            CMessage(message = message, userid = userid, channelid = channelid)
        }
    }

}

@Composable
private fun CMessage(message: PNHistoryItemResult, userid: String, channelid: String) {
    AyeTheme() {

        Log.i("cmessagedebug meta", message.meta.toString())
        Log.i("cmessagedebug entry", message.entry.toString())

        val metaData = Gson().fromJson<MessageMetaData>(message.meta, MessageMetaData::class.java)
        val entryData = Gson().fromJson<CEntryData>(message.entry, CEntryData::class.java)

        val pnConfiguration = PNConfiguration().apply {
            subscribeKey = "sub-c-d099e214-9bcf-11eb-9adf-f2e9c1644994"
            publishKey = "pub-c-a65bb691-5b8a-4c4b-aef5-e2a26677122d"
            secure = true
            uuid = userid
        }

        val pubnub = PubNub(pnConfiguration)

//        var name by remember { mutableStateOf("") }

        var urlOfFileHere by remember { mutableStateOf("")}
        var imageLinkFound by remember {
            mutableStateOf(false)
        }

        pubnub.getFileUrl(
            channel = channelid,
            fileName = entryData.file.name,
            fileId = entryData.file.id
        ).async { result, status ->
            if (status.error) {
                Log.i("cmessagedebug get url", status.error.toString())
            } else if (result != null) {
                urlOfFileHere = result.url
                Log.i("cmessagedebug get url", result.url)
                imageLinkFound = true
            }
        }

        if (imageLinkFound) {
            Box (modifier = Modifier
                .fillMaxWidth(0.95f)
                .height(250.dp)
                .padding(vertical = 5.dp)
                .clip(RoundedCornerShape(corner = CornerSize(15.dp))),
                contentAlignment = Alignment.Center
            ) {
                ImageHereForC(imageLink = urlOfFileHere)
                DPBubble(dplink = metaData.user_dp, text = message.entry.toString())
            }
        } else {
            Box (modifier = Modifier
                .fillMaxWidth(0.95f)
                .height(250.dp)
                .padding(vertical = 5.dp)
                .clip(RoundedCornerShape(corner = CornerSize(15.dp))),
                contentAlignment = Alignment.Center
            ) {
                DPBubble(dplink = metaData.user_dp, text = message.entry.toString())
            }
        }

    }
}

@Composable
private fun HMessage(message: PNHistoryItemResult) {
    AyeTheme() {

        val metaData = Gson().fromJson<MessageMetaData>(message.meta, MessageMetaData::class.java)

        Log.i("pubnub - only link", metaData.image_url)

        Box (modifier = Modifier
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
    Box(modifier = Modifier
        .clip(ChatBubbleShape)
        .background(AyeTheme.colors.uiSurface.copy(0.75f))) {
        Text(
            text = text,
            style = MaterialTheme.typography.body2,
            color = AyeTheme.colors.textSecondary,
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp
            )
        )
    }
}