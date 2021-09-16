package com.example.toastgoand.network.pnstuff

import com.pubnub.api.PNConfiguration
import com.pubnub.api.PubNub

val pnConfiguration = PNConfiguration().apply {
    subscribeKey = "sub-c-d099e214-9bcf-11eb-9adf-f2e9c1644994"
    publishKey = "pub-c-a65bb691-5b8a-4c4b-aef5-e2a26677122d"
    secure = true
    uuid = "82"
}

val pubNub = PubNub(pnConfiguration)

