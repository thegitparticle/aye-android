package com.example.toastgoand.home.directs.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
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
import com.example.toastgoand.home.directs.MyDirectDataClass
import com.example.toastgoand.utilities.drawColorShadow
import com.google.accompanist.appcompattheme.AppCompatTheme
import com.google.android.material.composethemeadapter.MdcTheme

@Composable
fun DirectItem (directItem: MyDirectDataClass) {
    AppCompatTheme() {
        Row () {
            DirectImage(directItem = directItem)
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(text = directItem.displayGuys.fullName, style = MaterialTheme.typography.subtitle1, color = Color.Black)
                Text(text = "tap to start new frame", style = MaterialTheme.typography.caption, color = Color.Blue)
            }
        }
    }
}

@Composable
fun DirectImage (directItem: MyDirectDataClass) {
    val painter = rememberImagePainter(data = directItem.displayGuys.profilePicture)
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