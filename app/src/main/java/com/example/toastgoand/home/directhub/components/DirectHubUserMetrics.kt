package com.example.toastgoand.home.directhub.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.toastgoand.home.directhub.DirectHubDataClass
import com.google.accompanist.appcompattheme.AppCompatTheme
import com.google.android.material.composethemeadapter.MdcTheme

@Composable
fun DirectHubUserMetrics (userProfile: DirectHubDataClass) {
    AppCompatTheme() {
        Row () {
            Text(text = userProfile.user.numberOfClubsJoined.toString(), style = MaterialTheme.typography.subtitle1, color = Color.Black)
            Text(text = userProfile.user.totalFramesParticipation.toString(), style = MaterialTheme.typography.caption, color = Color.Blue)
        }
    }
}
