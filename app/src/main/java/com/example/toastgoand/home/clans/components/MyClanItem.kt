package com.example.toastgoand.home.clans

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.toastgoand.home.clans.components.LiveClanItem
import com.example.toastgoand.utilities.drawColorShadow

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MyClanItem(myclan: MyClanDataClass) {
    if (myclan.ongoingFrame) {
        LiveClanItem(myclan = myclan)
    } else {
        DormantClan(myclan = myclan)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun DormantClan(myclan: MyClanDataClass) {
    Row () {
        ClanImage(myclan = myclan)
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(text = myclan.clubName, style = MaterialTheme.typography.subtitle1, color = Color.Black)
            Text(text = "tap to start new frame", style = MaterialTheme.typography.caption, color = Color.Blue)
        } 
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun ClanImage(myclan: MyClanDataClass) {
    val painter = rememberImagePainter(data = myclan.clubProfilePic)
    Image(
        painter = painter,
        contentDescription = "Forest Image",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(8.dp)
            .size(55.dp)
            .clip(RoundedCornerShape(corner = CornerSize(27.5.dp)))
            .drawColorShadow(Color.Blue)
    )
}
