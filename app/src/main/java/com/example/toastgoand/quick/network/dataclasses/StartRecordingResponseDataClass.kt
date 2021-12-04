package com.example.toastgoand.quick.network.dataclasses

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class StartRecordingResponseDataClass(
    val resourceId: String,
    val sid: String
)
