package com.example.toastgoand.uibits

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.network.directs.MyDirectsDataClass
import com.example.toastgoand.network.myclans.MyClansDataClass
import com.example.toastgoand.quick.QuickActivity

@Composable
fun TextRowButton(
    mainText: String,
    infoText: String,
    onPressed: () -> Unit = { },
    enabled: Boolean,
    infoColor: androidx.compose.ui.graphics.Color
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .clickable {
                onPressed()
            }
            .padding(vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        if (enabled) {
            Text(
                text = mainText,
                style = MaterialTheme.typography.subtitle1,
                color = AyeTheme.colors.textPrimary,
                modifier = Modifier.padding(horizontal = 20.dp)
            )
            Text(
                text = infoText,
                style = MaterialTheme.typography.subtitle1,
                color = infoColor,
                modifier = Modifier.padding(horizontal = 20.dp)
            )
        } else {
            Text(
                text = mainText,
                style = MaterialTheme.typography.subtitle1,
                color = AyeTheme.colors.textPrimary.copy(0.5f),
                modifier = Modifier.padding(horizontal = 20.dp)
            )
            Text(
                text = infoText,
                style = MaterialTheme.typography.subtitle1,
                color = infoColor.copy(0.5f),
                modifier = Modifier.padding(horizontal = 20.dp)
            )
        }
    }
}

@Composable
fun TextRowButtonClan(clan: MyClansDataClass, userid: Int) {

    val context = LocalContext.current

    if (clan.on_going_stream_status) {
        TextRowButton(
            mainText = clan.club_name,
            infoText = "stream ongoing",
            enabled = false,
            infoColor = AyeTheme.colors.success
        )
    } else {
        if (clan.ongoing_frame) {
            TextRowButton(
                mainText = clan.club_name,
                infoText = "frame ongoing",
                enabled = true,
                infoColor = AyeTheme.colors.appLead,
                onPressed = {
                    context.startActivity(
                        Intent(
                            context,
                            QuickActivity::class.java
                        ).apply {
                            putExtra("clubName", clan.club_name)
                            putExtra("clubid", clan.club_id)
                            putExtra("channelid", clan.pn_channel_id)
                            putExtra("ongoingFrame", clan.ongoing_frame)
                            putExtra("startTime", clan.start_time)
                            putExtra("endTime", clan.end_time)
                            putExtra("userid", userid)
                            putExtra("directornot", false)
                        })
                }
            )
        } else {
            TextRowButton(
                mainText = clan.club_name,
                infoText = "",
                enabled = true,
                infoColor = AyeTheme.colors.appLead,
                onPressed = {
                    context.startActivity(
                        Intent(
                            context,
                            QuickActivity::class.java
                        ).apply {
                            putExtra("clubName", clan.club_name)
                            putExtra("clubid", clan.club_id)
                            putExtra("channelid", clan.pn_channel_id)
                            putExtra("ongoingFrame", clan.ongoing_frame)
                            putExtra("startTime", clan.start_time)
                            putExtra("endTime", clan.end_time)
                            putExtra("userid", userid)
                            putExtra("directornot", false)
                        })
                }
            )
        }
    }
}

@Composable
fun TextRowButtonDirect(direct: MyDirectsDataClass, userid: Int) {

    val context = LocalContext.current

    if (direct.ongoing_frame) {
        TextRowButton(
            mainText = direct.display_guys.full_name,
            infoText = "frame ongoing",
            enabled = true,
            infoColor = AyeTheme.colors.appLead,
            onPressed = {
                context.startActivity(
                    Intent(
                        context,
                        QuickActivity::class.java
                    ).apply {
                        putExtra("clubName", direct.display_guys.full_name)
                        putExtra("channelid", direct.direct_channel_id)
                        putExtra("ongoingFrame", direct.ongoing_frame)
                        putExtra("startTime", direct.start_time)
                        putExtra("endTime", direct.end_time)
                        putExtra("directornot", true)
                        putExtra("userid", userid)
                    })
            }
        )
    } else {
        TextRowButton(
            mainText = direct.display_guys.full_name,
            infoText = "",
            enabled = true,
            infoColor = AyeTheme.colors.appLead,
            onPressed = {
                context.startActivity(
                    Intent(
                        context,
                        QuickActivity::class.java
                    ).apply {
                        putExtra("clubName", direct.display_guys.full_name)
                        putExtra("channelid", direct.direct_channel_id)
                        putExtra("ongoingFrame", direct.ongoing_frame)
                        putExtra("startTime", direct.start_time)
                        putExtra("endTime", direct.end_time)
                        putExtra("directornot", true)
                        putExtra("userid", userid)
                    })
            }
        )
    }
}