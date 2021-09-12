package com.example.toastgoand.uibits

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import com.google.accompanist.insets.ui.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.toastgoand.R
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import com.google.accompanist.insets.statusBarsPadding

@Composable
fun TopHeaderPlayScreens(
    modifier: Modifier = Modifier,
    onLeftIconPressed: () -> Unit = { },
    title: @Composable RowScope.() -> Unit,
    actions: @Composable RowScope.() -> Unit = {}
) {
    val backgroundColor = colorResource(id = R.color.light_splash)

    ProvideWindowInsets {
        Column(
            Modifier.background(backgroundColor.copy(alpha = 0.95f))
        ) {
            TopAppBar(
                modifier = modifier.statusBarsPadding(),
//                contentPadding = rememberInsetsPaddingValues(
//                    insets = LocalWindowInsets.current.statusBars,
//                    applyStart = true,
//                    applyTop = true,
//                    applyEnd = true,
//                ),
                backgroundColor = Color.Transparent,
                elevation = 0.dp, // No shadow needed
                contentColor = colorResource(id = R.color.off_dark_splash),
                actions = actions,
                title = { Row { title() } }, // https://issuetracker.google.com/168793068
                navigationIcon = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_invitepeople),
                        contentDescription = "go to frames",
                        modifier = Modifier
                        .clickable(onClick = onLeftIconPressed)
                            .padding(horizontal = 16.dp)
                    )
                }
            )
            Divider()
        }
    }
}