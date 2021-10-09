package com.example.toastgoand.home.clantalk.components

import android.content.Intent
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
import coil.compose.rememberImagePainter
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.uibits.ViewMediaActivity
import com.google.gson.Gson
import com.pubnub.api.PNConfiguration
import com.pubnub.api.PubNub
import com.pubnub.api.models.consumer.pubsub.PNMessageResult
import kotlinx.serialization.Serializable

@Serializable
data class UserMetaData(val type: String, val image_url: String, val user_dp: String)

@Serializable
data class CEntryDataNew(val message: String, val file: CEntryFileNew)

@Serializable
data class CEntryFileNew(val id: String, val name: String)

@Composable
fun NewPNMessage (message: PNMessageResult, userid: String, channelid: String) {
    AyeTheme() {

        val metaData = Gson().fromJson<UserMetaData>(message.userMetadata, UserMetaData::class.java)
        Log.i("CameraXBasic", message.toString())

        if (metaData.type == "h") {
            HMessage(message = message)
        } else if (metaData.type == "c") {
            Log.i("livemessage camera mess", message.toString())
            CMessage(message = message, userid = userid, channelid = channelid)
        }
    }
}

@Composable
private fun CMessage(message: PNMessageResult, userid: String, channelid: String) {
    AyeTheme() {

        Log.i("CameraXBasic", message.toString())

        val metaData = Gson().fromJson<UserMetaData>(message.userMetadata, UserMetaData::class.java)
        val entryData = Gson().fromJson<CEntryDataNew>(message.message, CEntryDataNew::class.java)

//        val pnConfiguration = PNConfiguration().apply {
//            subscribeKey = "sub-c-d099e214-9bcf-11eb-9adf-f2e9c1644994"
//            publishKey = "pub-c-a65bb691-5b8a-4c4b-aef5-e2a26677122d"
//            secure = true
//            uuid = userid
//        }
//
//        val pubnub = PubNub(pnConfiguration)
//
//        var urlOfFileHere by remember { mutableStateOf("") }
//        var imageLinkFound by remember {
//            mutableStateOf(false)
//        }
//
//        pubnub.getFileUrl(
//            channel = channelid,
//            fileName = entryData.file.name,
//            fileId = entryData.file.id
//        ).async { result, status ->
//            if (status.error) {
//                Log.i("oldpnmessage", status.error.toString())
//            } else if (result != null) {
//                urlOfFileHere = result.url
//                imageLinkFound = true
//            }
//        }

            Box (modifier = Modifier
                .fillMaxWidth(0.95f)
                .height(250.dp)
                .padding(vertical = 5.dp)
                .clip(RoundedCornerShape(corner = CornerSize(15.dp))),
                contentAlignment = Alignment.Center
            ) {
                ImageHere(imageLink = "")
                DPBubble(dplink = metaData.user_dp, text = message.message.toString())
            }

//    else {
//            Box (modifier = Modifier
//                .fillMaxWidth(0.95f)
//                .height(250.dp)
//                .padding(vertical = 5.dp)
//                .clip(RoundedCornerShape(corner = CornerSize(15.dp))),
//                contentAlignment = Alignment.Center
//            ) {
//                DPBubble(dplink = metaData.user_dp, text = message.message.toString())
//            }

    }
}

@Composable
private fun HMessage(message: PNMessageResult) {
    AyeTheme() {

        val metaData = Gson().fromJson<UserMetaData>(message.userMetadata, UserMetaData::class.java)

        Box (modifier = Modifier
            .fillMaxWidth(0.95f)
            .height(250.dp)
            .padding(vertical = 5.dp)
            .clip(RoundedCornerShape(corner = CornerSize(15.dp))),
            contentAlignment = Alignment.Center
        ) {
            ImageHere(imageLink = metaData.image_url)
            DPBubble(dplink = metaData.user_dp, text = message.message.toString())
        }
    }
}

@Composable
private fun ImageHere(imageLink: String) {
    val painter = rememberImagePainter(data = imageLink)
    val context = LocalContext.current
    Log.i("oldpnmessage", imageLink)

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
            color = AyeTheme.colors.textSecondary
        )
    }
}

