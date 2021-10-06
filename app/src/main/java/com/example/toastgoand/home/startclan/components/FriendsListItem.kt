package com.example.toastgoand.home.startclan.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
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
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .fillMaxWidth(0.9f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = selected.value,
                onCheckedChange = { selected.value = it;
                if (selected.value) {
                    addFunction(friendItem)
                } else {
                    removeFunction(friendItem)
                }
                },
                colors = CheckboxDefaults.colors(
                    checkedColor = AyeTheme.colors.appLeadVariant,
                    uncheckedColor = AyeTheme.colors.textSecondary.copy(0.5f),
                    checkmarkColor = AyeTheme.colors.uiBackground
                ),
                modifier = Modifier
            )
            Spacer(modifier = Modifier.size(10.dp))
            UserDPHere(dp = friendItem.profile_pic)
            Column(
                modifier = Modifier.padding(horizontal = 10.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = friendItem.name,
                    style = MaterialTheme.typography.subtitle2,
                    color = AyeTheme.colors.textPrimary.copy(0.5f)
                )
            }
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
            .padding(horizontal = 8.dp)
            .size(40.dp)
            .clip(RoundedCornerShape(corner = CornerSize(20.dp)))
    )
}