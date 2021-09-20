package com.example.toastgoand.home.clanhub.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.home.clanhub.ClanDetailsDataClass
import com.example.toastgoand.home.clanhub.ClanHubDataClass
import com.google.android.material.composethemeadapter.MdcTheme

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