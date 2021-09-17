package com.example.toastgoand.home.clantalk.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.toastgoand.home.clanhub.components.UsersListItem
import com.example.toastgoand.network.defaultrecos.DefaultRecosDataClass
import com.example.toastgoand.network.directs.MyDirectsDataClass


@Composable
fun RecoOverlay (defaultRecos: List<DefaultRecosDataClass>) {

    Log.i("defaultrecos", defaultRecos.toString())

    Row(
        modifier = Modifier.background(MaterialTheme.colors.surface),
        verticalAlignment = Alignment.CenterVertically
    ) {
        LazyRow() {
//            items(
//                items = defaultRecos[0],
//                itemContent = {
//                    RecoImage(it)
//                })
        }
    }

}

@Composable
fun RecoImage(imageLink: String) {
    val painter = rememberImagePainter(data = imageLink)
    Image(
        painter = painter,
        contentDescription = "Forest Image",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(8.dp)
            .size(width = 50.dp, height = 30.dp)
            .clip(RoundedCornerShape(corner = CornerSize(5.dp)))
    )
}