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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.home.clanframes.ClanFramesActivity
import com.example.toastgoand.network.directs.MyDirectsDataClass
import com.example.toastgoand.uibits.ViewMediaActivity
import com.example.toastgoand.utilities.drawColorShadow
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.pubnub.api.models.consumer.history.PNHistoryItemResult
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.decodeFromJsonElement

@Serializable
data class MessageMetaData(val image_url: String, val user_dp: String, val type: String)

@Composable
fun OldPNMessage (message: PNHistoryItemResult) {

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
        .background(MaterialTheme.colors.surface.copy(0.75f))) {
        Text(
            text = text,
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.onBackground
        )
    }
}