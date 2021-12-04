package com.example.toastgoand.home.clantalk.network

import androidx.annotation.Keep
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class GetSelectedRecosDataClass(
    val id: Int,
    val links: List<String>
)
