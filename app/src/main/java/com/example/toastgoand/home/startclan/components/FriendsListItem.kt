package com.example.toastgoand.home.startclan.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.network.myfriends.MyFriendsDataClass

@Composable
fun FriendsListItem(
    friendItem: MyFriendsDataClass,
    addFunction: (selectedItem: MyFriendsDataClass) -> Unit,
    removeFunction: (removedItem: MyFriendsDataClass) -> Unit
) {
    val selected = remember { mutableStateOf(false) }

    AyeTheme() {
        Row(modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth()) {
            Checkbox(
                checked = selected.value,
                onCheckedChange = { selected.value = it;
                if (selected.value) {
                    addFunction(friendItem)
                } else {
                    removeFunction(friendItem)
                }
                }
            )
            UserDPHere(dp = friendItem.profile_pic)
            Text(
                text = friendItem.name,
                style = MaterialTheme.typography.subtitle2,
                color = MaterialTheme.colors.onBackground
            )
        }
    }

}

@Composable
private fun UserDPHere(dp: String) {
    val painter = rememberImagePainter(data = dp)
    Image(
        painter = painter,
        contentDescription = "Forest Image",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(8.dp)
            .size(40.dp)
            .clip(RoundedCornerShape(corner = CornerSize(20.dp)))
    )
}