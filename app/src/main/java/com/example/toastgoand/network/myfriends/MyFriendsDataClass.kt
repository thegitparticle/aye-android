package com.example.toastgoand.network.myfriends

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(
    tableName = "myFriendsTable"
)

@Keep
@Serializable
data class MyFriendsDataClass(
    val name: String,
    @PrimaryKey val friend_user_id: Int,
    val profile_pic: String
)