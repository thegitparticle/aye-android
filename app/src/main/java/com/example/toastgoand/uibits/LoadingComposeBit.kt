package com.example.toastgoand.uibits

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.toastgoand.composestyle.AyeTheme

@Composable
fun LoadingComposeBit(
    modifier: Modifier = Modifier,
) {

    Row(
        modifier
            .shadow(2.dp, CircleShape)
            .clip(CircleShape)
            .background(AyeTheme.colors.uiSurface)
            .padding(0.dp, 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp)
                .size(16.dp),
            strokeWidth = 2.dp,
            color = AyeTheme.colors.appLeadVariant
        )
        Text(
            modifier = Modifier.padding(end = 12.dp),
            text = "Loading...",
            fontSize = 14.sp,
            color = AyeTheme.colors.textSecondary.copy(alpha = .75f)
        )
    }
}