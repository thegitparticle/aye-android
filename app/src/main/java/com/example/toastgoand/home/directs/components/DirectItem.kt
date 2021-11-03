package com.example.toastgoand.home.directs.components

import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DynamicFeed
import androidx.compose.material.icons.outlined.Layers
import androidx.compose.material.icons.rounded.Layers
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.home.clantalk.ClanTalkActivity
import com.example.toastgoand.network.directs.MyDirectsDataClass
import com.example.toastgoand.utilities.drawColorShadow
import compose.icons.FeatherIcons
import compose.icons.feathericons.Layers

@Composable
fun DirectItem(directItem: MyDirectsDataClass) {
    AyeTheme() {
        val context = LocalContext.current

        Row(modifier = Modifier
            .clickable {
                context.startActivity(Intent(context, ClanTalkActivity::class.java).apply {
                    putExtra("clubName", directItem.display_guys.full_name)
                    putExtra("channelid", directItem.direct_channel_id)
                    putExtra("ongoingFrame", directItem.ongoing_frame)
                    putExtra("startTime", directItem.start_time)
                    putExtra("endTime", directItem.end_time)
                    putExtra("directornot", true)
                })
            }) {
            Row(modifier = Modifier.padding(horizontal = 15.dp)) {
                DirectImage(directItem = directItem)
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = directItem.display_guys.full_name,
                        style = MaterialTheme.typography.subtitle1,
                        color = AyeTheme.colors.textPrimary
                    )
                    Row(
                        modifier = Modifier.padding(vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            FeatherIcons.Layers,
                            "Back",
                            tint = if (directItem.ongoing_frame) {
                                AyeTheme.colors.appLead
                            } else {
                                AyeTheme.colors.textSecondary.copy(0.25f)
                            },
                            modifier = Modifier.size(11.dp)
                        )
                        Text(
                            text = if (directItem.ongoing_frame) {
                                "frame going on"
                            } else {
                                "tap to start new frame"
                            },
                            style = MaterialTheme.typography.caption,
                            color = if (directItem.ongoing_frame) {
                                AyeTheme.colors.appLead
                            } else {
                                AyeTheme.colors.textSecondary.copy(0.25f)
                            },
                            modifier = Modifier
                                .padding(horizontal = 4.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DirectImage(directItem: MyDirectsDataClass) {
    val painter = rememberImagePainter(data = directItem.display_guys.profile_picture)
    val versionAPI = Build.VERSION.SDK_INT

    if (versionAPI >= 27) {
        Image(
            painter = painter,
            contentDescription = "Forest Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(8.dp)
                .size(55.dp)
                .clip(RoundedCornerShape(corner = CornerSize(27.5.dp)))
                .drawColorShadow(AyeTheme.colors.textSecondary, offsetY = 4.dp, alpha = 0.5f)
        )
    } else {
        Image(
            painter = painter,
            contentDescription = "Forest Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(8.dp)
                .size(55.dp)
                .clip(RoundedCornerShape(corner = CornerSize(27.5.dp)))
        )
    }
}