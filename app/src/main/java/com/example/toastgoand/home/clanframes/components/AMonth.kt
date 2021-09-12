package com.example.toastgoand.home.clanframes.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import com.google.accompanist.appcompattheme.AppCompatTheme
import kotlinx.datetime.*

@Composable
fun AMonth (viewMonth: Int, currentMonth: Int, currentDate: Int) {

    if (viewMonth == currentMonth) {
        AppCompatTheme () {
            if (currentDate <= 10) {
                Row (horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically) {
                    AStrip(start = 1, end = currentDate)
                }
            } else if (currentDate in 11..20) {
                Row (horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically) {
                    AStrip(start = 1, end = 10)
                    AStrip(start = 11, end = currentDate)
                }
            } else {
                Row (horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically) {
                    AStrip(start = 1, end = 10)
                    AStrip(start = 11, end = 20)
                    AStrip(start = 21, end = currentDate)
                }
            }
        }
    } else {
        AppCompatTheme () {
            Row (horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically) {
                AStrip(start = 1, end = 10)
                AStrip(start = 11, end = 20)
                AStrip(start = 21, end = 31)
            }
        }
    }

}