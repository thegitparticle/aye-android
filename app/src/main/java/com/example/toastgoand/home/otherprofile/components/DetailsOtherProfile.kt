package com.example.toastgoand.home.otherprofile.components

import androidx.compose.foundation.Image
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
import com.example.toastgoand.home.otherprofile.OtherProfileDataClass
import com.example.toastgoand.network.userdetails.UserDetailsDataClass

@Composable
fun DetailsOtherProfile (userDeets: OtherProfileDataClass) {
    AyeTheme() {
        Column(
            modifier = Modifier.fillMaxHeight(0.5f).fillMaxWidth(),
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
private fun DPImage(deets: OtherProfileDataClass) {
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