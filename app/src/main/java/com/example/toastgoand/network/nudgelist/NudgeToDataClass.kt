package com.example.toastgoand.network.nudgelist

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable


@Entity(
    tableName = "nudgeToTable"
)

data class NudgeToDataClass(
    val name: String,
    @PrimaryKey val id: Int,
    val profile_pic: String
)