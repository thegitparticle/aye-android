package com.example.toastgoand.home.directs.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
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

@Composable
fun NudgeToItem(nudgeItem: NudgeToDataClass) {
    AyeTheme() {
        Row(
            modifier = Modifier.padding(horizontal = 15.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row() {
                NudgeToImage(nudgeItem = nudgeItem)
                Column(
                    modifier = Modifier
                        .padding(16.dp)
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
    Button(
        onClick = { /* Do something! */ },
        modifier = Modifier.alpha(0.25F),
        colors = ButtonDefaults.textButtonColors(
            backgroundColor = MaterialTheme.colors.primaryVariant,
        ),
        shape = MaterialTheme.shapes.small
    ) {
        Text(
            text = "start",
            style = MaterialTheme.typography.subtitle2,
            color = MaterialTheme.colors.onSecondary
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