package com.example.toastgoand.home.startclan.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import com.example.toastgoand.network.myfriends.MyFriendsDataClass
import com.google.accompanist.appcompattheme.AppCompatTheme

@Composable
fun FriendsListItem (friendItem: MyFriendsDataClass) {

    AppCompatTheme () {
        Row() {
            Text(friendItem.name, modifier = Modifier.fillMaxWidth())
        }
    }

}