package com.example.toastgoand.network.defaultrecos

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(
    tableName = "defaultRecosTable"
)

@Serializable
data class DefaultRecosDataClass(
    @PrimaryKey val recoset: List<String>
)
