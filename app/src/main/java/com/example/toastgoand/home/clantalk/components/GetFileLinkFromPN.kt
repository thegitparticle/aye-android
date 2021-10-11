package com.example.toastgoand.home.clantalk.components

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.pubnub.api.PNConfiguration
import com.pubnub.api.PubNub
import javax.security.auth.callback.Callback

fun getFileLinkFromPN (userid: String, channelid: String, filename: String, fileid: String, callback: (String) -> Unit) {
    val pnConfiguration = PNConfiguration().apply {
        subscribeKey = "sub-c-d099e214-9bcf-11eb-9adf-f2e9c1644994"
        publishKey = "pub-c-a65bb691-5b8a-4c4b-aef5-e2a26677122d"
        secure = true
        uuid = userid
    }

    var fileLinkHere: String = ""

    val pubnub = PubNub(pnConfiguration)

    pubnub.getFileUrl(
        channel = channelid,
        fileName = filename,
        fileId = fileid
    ).async { result, status ->
        if (status.error) {
            Log.i("cmessagedebug get url error", status.error.toString())
            fileLinkHere = "error"
            callback("error")
        } else if (result != null) {
            Log.i("cmessagedebug got url", result.url.toString())
            fileLinkHere = result.url
            callback(result.url)
        }
    }

}