package com.example.toastgoand.uibits

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
import androidx.compose.ui.unit.dp
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.network.directs.MyDirectsDataClass
import com.example.toastgoand.network.myclans.MyClansDataClass

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
fun TextRowButtonClan(clan: MyClansDataClass) {
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
                infoColor = AyeTheme.colors.appLead
            )
        } else {
            TextRowButton(
                mainText = clan.club_name,
                infoText = "",
                enabled = true,
                infoColor = AyeTheme.colors.appLead
            )
        }
    }
}

@Composable
fun TextRowButtonDirect(direct: MyDirectsDataClass) {
    if (direct.ongoing_frame) {
        TextRowButton(
            mainText = direct.display_guys.full_name,
            infoText = "frame ongoing",
            enabled = false,
            infoColor = AyeTheme.colors.appLead
        )
    } else {
        TextRowButton(
            mainText = direct.display_guys.full_name,
            infoText = "",
            enabled = true,
            infoColor = AyeTheme.colors.appLead
        )
    }
}