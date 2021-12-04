package com.example.toastgoand.home.clantalk.components

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class SMessageMetaDataClass(
    val type: String,
    val image_url: String,
    val user_dp: String
)
