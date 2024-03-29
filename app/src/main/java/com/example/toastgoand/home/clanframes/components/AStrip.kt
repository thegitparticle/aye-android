package com.example.toastgoand.home.clanframes.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.home.clanframes.ClanFrameDataClass

@Composable
fun AStrip(
    start: Int, end: Int, framesList: List<ClanFrameDataClass>, clubName: String,
    userid: String, channelid: String
) {

    val datesArray = mutableListOf<Int>()

    for (i in start..end) {
        datesArray.add(i)
    }

    AyeTheme() {
        Box(Modifier.width(100.dp), contentAlignment = Alignment.TopCenter) {
            Box(
                Modifier
                    .width(
                        32.dp
                    )
                    .background(
                        if (start < 3) {
                            AyeTheme.colors.brandVariant.copy(0.5f)
                        } else if (start == 11) {
                            AyeTheme.colors.appLeadVariant.copy(0.5f)
                        } else {
                            AyeTheme.colors.success.copy(0.5f)
                        }
                    )
                    .padding(horizontal = 50.dp)
                    .height(400.dp)
            )
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                for (date in datesArray) {
                    ADay(
                        date,
                        framesList,
                        clubName = clubName,
                        userid = userid,
                        channelid = channelid
                    )
                }
            }
        }
    }
}
