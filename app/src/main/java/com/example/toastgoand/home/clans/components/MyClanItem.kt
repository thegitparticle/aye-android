package com.example.toastgoand.home.clans

import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DynamicFeed
import androidx.compose.material.icons.outlined.Layers
import androidx.compose.material.icons.rounded.Layers
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.home.clantalk.ClanTalkActivity
import com.example.toastgoand.network.myclans.MyClansDataClass
import com.example.toastgoand.utilities.drawColorShadow
import compose.icons.FeatherIcons
import compose.icons.feathericons.Layers


@Composable
fun DormantClan(myclan: MyClansDataClass) {
    val context = LocalContext.current
    val isDarkTheme = remember { mutableStateOf(false) }

    AyeTheme() {

        Row(modifier = Modifier.clickable {
            context.startActivity(Intent(context, ClanTalkActivity::class.java).apply {
                putExtra("clubName", myclan.club_name)
                putExtra("clubid", myclan.club_id)
                putExtra("channelid", myclan.pn_channel_id)
                putExtra("ongoingFrame", myclan.ongoing_frame)
                putExtra("startTime", myclan.start_time)
                putExtra("endTime", myclan.end_time)
                putExtra("ongoingStream", myclan.on_going_stream_status)
                putExtra("ongoingStreamUser", myclan.stream_started_by)
                putExtra("directornot", false)
            })
        }) {
            Row(modifier = Modifier.padding(horizontal = 15.dp)) {
                ClanImage(myclan = myclan)
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = myclan.club_name,
                        style = typography.subtitle1,
                        color = AyeTheme.colors.textPrimary
                    )
                    Row(
                        modifier = Modifier.padding(vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            FeatherIcons.Layers,
                            "Back",
                            tint = AyeTheme.colors.textSecondary.copy(0.25f),
                            modifier = Modifier.size(11.dp)
                        )

                        Text(
                            text = "tap to start new frame",
                            style = typography.caption,
                            color = AyeTheme.colors.textSecondary,
                            modifier = Modifier
                                .alpha(
                                    0.25F
                                )
                                .padding(horizontal = 4.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ClanImage(myclan: MyClansDataClass) {
    val painter = rememberImagePainter(data = myclan.club_profile_pic)
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
