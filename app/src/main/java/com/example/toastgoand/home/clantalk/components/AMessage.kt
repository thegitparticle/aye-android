package com.example.toastgoand.home.clantalk.components

import androidx.compose.runtime.Composable
import com.example.toastgoand.composestyle.AyeTheme
import com.google.gson.Gson
import com.pubnub.api.models.consumer.history.PNHistoryItemResult

@Composable
fun AMessage (message: PNHistoryItemResult) {
    AyeTheme() {

        val metaData = Gson().fromJson<MessageMetaData>(message.meta, MessageMetaData::class.java)
        
        if (metaData.type == "h") {
            OldPNMessage(message = message)
        }
    }
}