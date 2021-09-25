package com.example.toastgoand.uibits

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import com.google.accompanist.insets.ui.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.toastgoand.composestyle.AyeTheme
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.google.accompanist.insets.statusBarsPadding
import compose.icons.FeatherIcons
import compose.icons.feathericons.*
import compose.icons.feathericons.ChevronRight

@Composable
fun HeaderPlayScreens(
    modifier: Modifier = Modifier,
    title: String,
    onBackIconPressed: () -> Unit = { },
    onActionIconPressed: () -> Unit = { },
    actionIcon: ImageVector
) {
    ProvideWindowInsets {
        TopAppBar(
            modifier = modifier
                .fillMaxWidth()
                .height(100.dp)
            ,
            contentPadding = rememberInsetsPaddingValues(
                LocalWindowInsets.current.statusBars,
                applyBottom = false,
            ),
            title = {
                Row(
                    verticalAlignment = Alignment.Bottom,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp, start = 15.dp, end = 30.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    CircleIcon(iconName = actionIcon, onIconPressed = onActionIconPressed, modifier = Modifier)
                    Text(
                        text = title,
                        style = MaterialTheme.typography.subtitle1,
                        color = MaterialTheme.colors.onBackground
                    )
                    CircleIcon(iconName = FeatherIcons.ChevronDown, onIconPressed = onBackIconPressed, modifier = Modifier)
                }
            },
            backgroundColor = AyeTheme.colors.uiBackground.copy(alpha = 0.95f),
            elevation = 4.dp
        )
    }
}