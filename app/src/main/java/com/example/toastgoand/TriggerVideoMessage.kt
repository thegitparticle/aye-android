package com.example.toastgoand

import android.content.Intent
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.home.clantalk.components.HMessageMetaDataClass
import com.example.toastgoand.home.clantalk.components.SMessageMetaDataClass
import com.example.toastgoand.home.startclan.StartClanActivity
import com.example.toastgoand.quick.QuickActivity
import com.example.toastgoand.quick.WatchStreamActivity
import com.pubnub.api.PNConfiguration
import com.pubnub.api.PubNub

@Composable
fun TriggerVideoMessage() {
    val context = LocalContext.current

    val pnConfiguration = PNConfiguration().apply {
        subscribeKey = "sub-c-d099e214-9bcf-11eb-9adf-f2e9c1644994"
        publishKey = "pub-c-a65bb691-5b8a-4c4b-aef5-e2a26677122d"
        secure = true
        uuid = "82"
    }

    val pubnub = PubNub(pnConfiguration)

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {

                context.startActivity(
                    Intent(
                        context,
                        WatchStreamActivity::class.java
                    ).apply {
                        putExtra("clubName", "Pope and Test")
                        putExtra("clubid", 90)
                        putExtra("channelid", "90_c")
                        putExtra("ongoingFrame", true)
                        putExtra("startTime", "")
                        putExtra("endTime", "")
                        putExtra(
                            "userid",
                            112
                        )
                        putExtra("directornot", false)
                    })


//                pubnub
//                    .publish(
//                        message = "rocking ....",
//                        channel = "90_c",
//                        meta = SMessageMetaDataClass(
//                            type = "s",
//                            image_url = "https://i.imgur.com/nqdirDy.mp4",
//                            user_dp = "https://aye-media-bucket.s3.amazonaws.com/media/profile_pics/IMG_0032.JPG"
//                        )
//                    ).async { result, status ->
//                        if (!status.error) {
//                           Log.i("triggervideo", "yes, sent")
//                        } else {
//                            Log.i("triggervideo", "didnt work")
//                        }
//                    }
            },
            colors = ButtonDefaults.textButtonColors(
                backgroundColor = AyeTheme.colors.appLead,
            ),
            shape = RoundedCornerShape(30.dp),
            modifier = Modifier
                .padding(vertical = 30.dp)
                .height(60.dp)
                .width(160.dp),
        ) {
            Text(
                "push video",
                style = MaterialTheme.typography.subtitle1,
                color = AyeTheme.colors.uiBackground
            )
        }
    }

}