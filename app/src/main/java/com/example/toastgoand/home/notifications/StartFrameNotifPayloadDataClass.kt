package com.example.toastgoand.home.notifications

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class StartFrameNotifPayloadDataClass(
    val pc_gcm: Payload
)

@Keep
@Serializable
data class Payload(
    val data: ChannelData,
    val notification: NotificationData
)

@Keep
@Serializable
data class ChannelData(
    val channel: String
)

@Keep
@Serializable
data class NotificationData(
    val title: String,
    val body: String,
    val sound: String
)
