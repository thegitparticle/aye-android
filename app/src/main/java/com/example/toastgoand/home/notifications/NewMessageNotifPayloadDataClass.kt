package com.example.toastgoand.home.notifications

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class NewMessageNotifPayloadDataClass(
    val pc_gcm: PayloadNewMessage
)

@Keep
@Serializable
data class PayloadNewMessage(
    val data: ChannelDataNewMessage,
    val notification: NotificationDataNewMessage
)

@Keep
@Serializable
data class ChannelDataNewMessage(
    val channel: String
)

@Keep
@Serializable
data class NotificationDataNewMessage(
    val title: String,
    val body: String,
    val sound: String
)