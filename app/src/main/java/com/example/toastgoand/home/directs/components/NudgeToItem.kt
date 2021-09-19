package com.example.toastgoand.home.directs.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.ui.layout.MainAxisAlignment
import coil.compose.rememberImagePainter
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.home.directs.NudgeItemDataClass
import com.example.toastgoand.network.nudgelist.NudgeToDataClass
import com.example.toastgoand.utilities.drawColorShadow
import com.google.android.material.composethemeadapter.MdcTheme

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NudgeToItem(nudgeItem: NudgeToDataClass) {
    AyeTheme() {
        Row(
            modifier = Modifier.padding(horizontal = 15.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row() {
                NudgeToImage(nudgeItem = nudgeItem)
                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                ) {
                    Text(
                        text = nudgeItem.name,
                        style = MaterialTheme.typography.subtitle1,
                        color = MaterialTheme.colors.onBackground
                    )
                }
            }
            StartButton()
        }
    }
}

@Composable
private fun StartButton() {
    Box(
        modifier = Modifier
            .wrapContentWidth()
            .clip(RoundedCornerShape(15.dp))
            .background(MaterialTheme.colors.primary.copy(.08f))
            .clickable { /* Do something! */ }
            .width(75.dp)
            .height(30.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "start",
            style = MaterialTheme.typography.caption,
            color = MaterialTheme.colors.primary
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun NudgeToImage(nudgeItem: NudgeToDataClass) {
    val painter = rememberImagePainter(data = nudgeItem.profile_pic)
    Image(
        painter = painter,
        contentDescription = "Forest Image",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(8.dp)
            .size(55.dp)
            .clip(RoundedCornerShape(corner = CornerSize(27.5.dp)))
            .drawColorShadow(MaterialTheme.colors.onBackground, offsetY = 4.dp, alpha = 0.5f)
    )
}