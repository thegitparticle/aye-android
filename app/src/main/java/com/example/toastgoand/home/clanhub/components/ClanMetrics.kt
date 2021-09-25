package com.example.toastgoand.home.clanhub.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.home.clanhub.ClanDetailsDataClass

@Composable
fun ClanMetrics (clanHub: ClanDetailsDataClass) {
    AyeTheme() {

        Box(
            modifier = Modifier
                .wrapContentWidth()
                .clip(RoundedCornerShape(15.dp))
                .background(MaterialTheme.colors.primary.copy(.08f))
                .width(100.dp)
                .height(50.dp)
                .padding(vertical = 15.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "${clanHub.frames_total}",
                style = MaterialTheme.typography.h5,
                color = MaterialTheme.colors.primary
            )
        }

    }
}