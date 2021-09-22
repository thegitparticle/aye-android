package com.example.toastgoand.home.notifications

data class NewMessageNotifPayloadDataClass(
    val pc_gcm: PayloadNewMessage
)

data class PayloadNewMessage(
    val data: ChannelDataNewMessage,
    val notification: NotificationDataNewMessage
)

data class ChannelDataNewMessage(
    val channel: String
)

data class NotificationDataNewMessage(
    val title: String,
    val body: String,
    val sound: String
)