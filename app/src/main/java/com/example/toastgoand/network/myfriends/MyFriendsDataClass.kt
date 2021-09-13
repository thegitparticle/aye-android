package com.example.toastgoand.network.myfriends

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "myFriendsTable"
)

data class MyFriendsDataClass(
    val name: String,
    @PrimaryKey val friend_user_id: Int,
    val profile_pic: String
)