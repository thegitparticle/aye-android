package com.example.toastgoand.network.pnstuff

import android.util.Log
import com.example.toastgoand.network.directs.MyDirectsDataClass
import com.example.toastgoand.network.myclans.MyClansDataClass
import com.pubnub.api.PNConfiguration
import com.pubnub.api.PubNub
import com.pubnub.api.enums.PNPushType

fun pushSetupClans(clansHere: MutableList<MyClansDataClass>, userid: String, deviceid: String) {

    val pnConfiguration = PNConfiguration().apply {
        subscribeKey = "sub-c-d099e214-9bcf-11eb-9adf-f2e9c1644994"
        publishKey = "pub-c-a65bb691-5b8a-4c4b-aef5-e2a26677122d"
        secure = true
        uuid = userid
    }

    val pubnub = PubNub(pnConfiguration)

    var Channels: MutableList<String> = mutableListOf()

    for (item in clansHere) {
        Channels.add(item.pn_channel_id + "_push")
    }

    Log.i("pnnotif clans", "function called")

    pubnub.addPushNotificationsOnChannels(
        pushType = PNPushType.FCM,
        channels = Channels,
        deviceId = deviceid,
    ).async{result, status ->
        if (status.error) {
            Log.i("pnnotif clans fail", status.statusCode.toString())
        } else {
            Log.i("pnnotif clans suc", result.toString() )
        }
    }
}

fun pushSetupDirects(directsHere: MutableList<MyDirectsDataClass>, userid: String, deviceid: String) {

    val pnConfiguration = PNConfiguration().apply {
        subscribeKey = "sub-c-d099e214-9bcf-11eb-9adf-f2e9c1644994"
        publishKey = "pub-c-a65bb691-5b8a-4c4b-aef5-e2a26677122d"
        secure = true
        uuid = userid
    }

    val pubnub = PubNub(pnConfiguration)

    var DirectChannels: MutableList<String> = mutableListOf()

    for (item in directsHere) {
        DirectChannels.add(item.direct_channel_id)
    }

    pubnub.addPushNotificationsOnChannels(
        pushType = PNPushType.FCM,
        channels = DirectChannels,
        deviceId = deviceid,
    ).async{result, status ->}
}