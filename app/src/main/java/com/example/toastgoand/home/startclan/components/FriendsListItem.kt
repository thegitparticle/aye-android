package com.example.toastgoand.home.startclan.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.network.myfriends.MyFriendsDataClass

@Composable
fun FriendsListItem (friendItem: MyFriendsDataClass) {

    AyeTheme () {
        Row() {
            Text(friendItem.name, modifier = Modifier.fillMaxWidth())
        }
    }

}