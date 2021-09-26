package com.example.toastgoand.home.clans.components

import android.content.Intent
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
import com.example.toastgoand.home.startclan.StartClanActivity

@Composable
fun StartClanButton () {
    val context = LocalContext.current

    Row(
        modifier = Modifier.fillMaxWidth().clickable {
            context?.startActivity(
                Intent(
                    context,
                    StartClanActivity::class.java
                ).apply { })
        },
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                context?.startActivity(
                    Intent(
                        context,
                        StartClanActivity::class.java
                    ).apply { })
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
                "start clan",
                style = MaterialTheme.typography.subtitle1,
                color = AyeTheme.colors.uiBackground
            )
        }
    }
}