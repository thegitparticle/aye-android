package com.example.toastgoand.home.notifications

data class StartFrameNotifPayloadDataClass(
    val pc_gcm: Payload
)

data class Payload(
    val data: ChannelData,
    val notification: NotificationData
)

data class ChannelData(
    val channel: String
)

data class NotificationData(
    val title: String,
    val body: String,
    val sound: String
)
