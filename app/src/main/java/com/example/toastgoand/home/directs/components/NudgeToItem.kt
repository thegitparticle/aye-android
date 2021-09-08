package com.example.toastgoand.home.directs.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.toastgoand.home.directs.NudgeItemDataClass
import com.google.accompanist.appcompattheme.AppCompatTheme
import com.google.android.material.composethemeadapter.MdcTheme

@Composable
fun NudgeToItem (nudgeItem: NudgeItemDataClass) {
    AppCompatTheme() {
        Row {
            NudgeToImage(nudgeItem = nudgeItem)
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(text = nudgeItem.name, style = MaterialTheme.typography.subtitle1, color = Color.Black)
            }
            StartButton()
        }
    }
}

@Composable
private fun StartButton() {
    Button(onClick = { /* Do something! */ }, colors = ButtonDefaults.textButtonColors(
        backgroundColor = Color.Red
    )) {
        Text("start")
    }
}

@Composable
private fun NudgeToImage (nudgeItem: NudgeItemDataClass) {
    val painter = rememberImagePainter(data = nudgeItem.profilePic)
    Image(
        painter = painter,
        contentDescription = "Forest Image",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(8.dp)
            .size(55.dp)
            .clip(RoundedCornerShape(corner = CornerSize(27.5.dp)))
    )
}