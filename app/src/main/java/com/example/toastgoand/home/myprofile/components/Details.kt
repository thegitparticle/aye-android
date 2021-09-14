package com.example.toastgoand.home.myprofile.components

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
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.network.myclans.MyClansDataClass
import com.example.toastgoand.network.userdetails.UserDetailsDataClass
import com.example.toastgoand.utilities.drawColorShadow

@Composable
fun Details (userDeets: UserDetailsDataClass) {
    AyeTheme() {
        DPImage(deets = userDeets)
        Text(text = userDeets.user.full_name, style = MaterialTheme.typography.subtitle1, color = Color.Black)
        Text(text = userDeets.user.username, style = MaterialTheme.typography.caption, color = Color.Blue)
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
            .size(55.dp)
            .clip(RoundedCornerShape(corner = CornerSize(27.5.dp)))
    )
}