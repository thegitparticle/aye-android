package com.example.toastgoand.home.clanframes.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.toastgoand.home.clanframes.ClanFrameDataClass
import com.google.accompanist.appcompattheme.AppCompatTheme

@Composable
fun ADay (date: Int, framesList: List<ClanFrameDataClass>) {
    AppCompatTheme () {
        val checkResult = checkForFrames(date, framesList)

        if (checkResult.size > 0) {
            for (item in checkResult) {
                FrameImage(item.frame_picture_link)
            }
        } else {
            Text(text = date.toString(), style = MaterialTheme.typography.subtitle1, color = Color.Black)
        }
    }
}

private fun checkForFrames(date: Int, framesList: List<ClanFrameDataClass>): MutableList<ClanFrameDataClass> {

val framesListHere = framesList

    val datesArray = mutableListOf<ClanFrameDataClass>()

    for (item in framesListHere) {
        if (item.published_date.takeLast(2) == date.toString() || item.published_date.takeLast(1) == date.toString()) {
            datesArray.add(item)
        }
    }

    return datesArray
}

@Composable
private fun FrameImage (link: String) {
    val painter = rememberImagePainter(data = link)
    Image(
        painter = painter,
        contentDescription = "clan frame thumbnail",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(8.dp)
            .size(50.dp)
            .clip(RoundedCornerShape(corner = CornerSize(10.dp)))
    )
}