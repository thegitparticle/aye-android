package com.example.toastgoand.home.directframes.components

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
import com.example.toastgoand.home.directframes.DirectFrameDataClass
import com.example.toastgoand.home.directframes.ViewOldFrameDirectActivity

@Composable
fun ADayDirect(
    date: Int, framesList: List<DirectFrameDataClass>, otherName: String,
    userid: String, directid: String
) {
    AyeTheme() {
        val checkResult = checkForFrames(date, framesList = framesList)

        if (checkResult.size > 0) {
            for (item in checkResult) {
                FrameImage(
                    item, directid = directid,
                    otherName = otherName,
                    userid = userid
                )
            }
        } else {
            Text(
                modifier = Modifier.padding(vertical = 20.dp),
                text = date.toString(),
                style = MaterialTheme.typography.subtitle1,
                color = MaterialTheme.colors.background.copy(0.5f)
            )
        }
    }
}

private fun checkForFrames(
    date: Int,
    framesList: List<DirectFrameDataClass>
): MutableList<DirectFrameDataClass> {

    val framesListHere = framesList

    val datesArray = mutableListOf<DirectFrameDataClass>()

    for (item in framesListHere) {
        if (item.published_date.takeLast(2) == date.toString() || item.published_date.takeLast(2) == "0$date") {
            datesArray.add(item)
        }
    }

    return datesArray
}

@Composable
private fun FrameImage(
    item: DirectFrameDataClass, otherName: String,
    userid: String, directid: String
) {
    val painter = rememberImagePainter(data = item.frame_picture_link)
    val context = LocalContext.current

    Image(
        painter = painter,
        contentDescription = "direct frame thumbnail",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(vertical = 20.dp )
            .size(60.dp)
            .clip(RoundedCornerShape(corner = CornerSize(12.dp)))
            .clickable {
                context.startActivity(
                    Intent(
                        context,
                        ViewOldFrameDirectActivity::class.java
                    ).apply {
                        putExtra("startTime", item.start_time)
                        putExtra("endTime", item.end_time)
                        putExtra("userid", userid)
                        putExtra("otherName", otherName)
                        putExtra("directid", directid)
                    })
            }
    )
}