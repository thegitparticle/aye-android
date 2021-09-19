package com.example.toastgoand.home.clanframes.components

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.home.clanframes.ClanFrameDataClass
import com.example.toastgoand.home.clanframes.ViewOldFrameClanActivity
import com.example.toastgoand.uibits.ViewMediaActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder

@Composable
fun ADay(date: Int, framesList: List<ClanFrameDataClass>,  clubName: String,
         userid: String) {
    AyeTheme() {
        val checkResult = checkForFrames(date, framesList)

        if (checkResult.size > 0) {
            for (item in checkResult) {
                FrameImage(item, clubName, userid)
            }
        } else {
            Text(
                text = date.toString(),
                style = MaterialTheme.typography.subtitle1,
                color = MaterialTheme.colors.background.copy(0.5f)
            )
        }
    }
}

private fun checkForFrames(
    date: Int,
    framesList: List<ClanFrameDataClass>
): MutableList<ClanFrameDataClass> {

    val framesListHere = framesList

    val datesArray = mutableListOf<ClanFrameDataClass>()

    for (item in framesListHere) {
        if (item.published_date.takeLast(2) == date.toString() || item.published_date.takeLast(2) == "0$date") {
            datesArray.add(item)
        }
    }

    return datesArray
}

@Composable
private fun FrameImage(item: ClanFrameDataClass,  clubName: String,
                       userid: String) {
    val painter = rememberImagePainter(data = item.frame_picture_link)
    val context = LocalContext.current

    Image(
        painter = painter,
        contentDescription = "clan frame thumbnail",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(60.dp)
            .clip(RoundedCornerShape(corner = CornerSize(12.dp)))
            .clickable {
                context.startActivity(
                    Intent(
                        context,
                        ViewOldFrameClanActivity::class.java
                    ).apply {
                        putExtra("startTime", item.start_time)
                        putExtra("endTime", item.end_time)
                        putExtra("userid", userid)
                        putExtra("clubName", clubName)
                    })
            }
    )
}