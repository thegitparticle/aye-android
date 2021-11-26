package com.example.toastgoand.network.nudgelist

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable


@Entity(
    tableName = "nudgeToTable"
)

@Keep
@Serializable
data class NudgeToDataClass(
    val name: String,
    @PrimaryKey val id: Int,
    val profile_pic: String
)