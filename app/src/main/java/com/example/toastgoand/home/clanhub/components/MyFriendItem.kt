package com.example.toastgoand.home.clanhub.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults.colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.colors
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
import com.example.toastgoand.composestyle.AyeTheme.colors
import com.example.toastgoand.network.myfriends.MyFriendsDataClass

@Composable
fun MyFriendItem(
    friendItem: MyFriendsDataClass,
    addFunction: (selectedItem: MyFriendsDataClass) -> Unit,
    removeFunction: (removedItem: MyFriendsDataClass) -> Unit
) {
    val selected = remember { mutableStateOf(false) }

    AyeTheme() {
        Row(
            modifier = Modifier.padding(horizontal = 20.dp).clickable {
                selected.value = (!selected.value)
                if (!selected.value) {
                    addFunction(friendItem)
                } else {
                    removeFunction(friendItem)
                }
            },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = selected.value,
                onCheckedChange = {
                    selected.value = it
                    if (it) {
                        addFunction(friendItem)
                    } else {
                        removeFunction(friendItem)
                    }
                },
                colors = colors(
                    checkedColor = AyeTheme.colors.appLeadVariant,
                    uncheckedColor = AyeTheme.colors.textSecondary.copy(0.5f),
                    checkmarkColor = AyeTheme.colors.uiBackground
                ),
                modifier = Modifier
            )
            Spacer(modifier = Modifier.size(10.dp))
            UserDPHere(dp = friendItem.profile_pic)
            Spacer(modifier = Modifier.size(10.dp))
            Text(
                text = friendItem.name,
                style = MaterialTheme.typography.body1,
                color = AyeTheme.colors.textPrimary
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
            .size(50.dp)
            .clip(RoundedCornerShape(corner = CornerSize(25.dp)))
    )
}