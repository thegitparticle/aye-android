package com.example.toastgoand.home.directhub.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
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
import com.example.toastgoand.home.directhub.DirectHubDataClass

@Composable
fun DirectHubUserDetails (userProfile: DirectHubDataClass) {
    AyeTheme() {
        Row () {
            UserDP(userProfile.image)
            Text(text = userProfile.user.fullName, style = MaterialTheme.typography.subtitle1, color = Color.Black)
            Text(text = userProfile.user.username, style = MaterialTheme.typography.caption, color = Color.Blue)
        }
    }
}

@Composable
private fun UserDP (dp: String) {
    val painter = rememberImagePainter(data = dp)
    Image(
        painter = painter,
        contentDescription = "direct hub other user dp",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(8.dp)
            .size(55.dp)
            .clip(RoundedCornerShape(corner = CornerSize(27.5.dp)))
    )
}