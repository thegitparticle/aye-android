package com.example.toastgoand.home.myprofile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.network.userdetails.UserDetailsDataClass

@Composable
fun Details(userDeets: UserDetailsDataClass) {
    AyeTheme() {
        Column(
            modifier = Modifier.fillMaxHeight(0.5f).fillMaxWidth().background(color = AyeTheme.colors.uiBackground),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            DPImage(deets = userDeets)
            Text(
                text = userDeets.user.full_name,
                style = MaterialTheme.typography.subtitle1,
                color = Color.Black,
                modifier = Modifier.padding(top = 20.dp, bottom = 5.dp)
            )
            Text(
                text = userDeets.user.username,
                style = MaterialTheme.typography.caption,
                color = Color.Blue,
                modifier = Modifier.padding(top = 5.dp, bottom = 20.dp)
            )
        }
    }
}

@Composable
private fun DPImage(deets: UserDetailsDataClass) {
    val painter = rememberImagePainter(data = deets.image)
    Image(
        painter = painter,
        contentDescription = "Forest Image",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(8.dp)
            .size(100.dp)
            .clip(RoundedCornerShape(corner = CornerSize(50.dp)))

    )
}