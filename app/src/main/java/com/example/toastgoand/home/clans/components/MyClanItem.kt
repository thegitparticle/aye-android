package com.example.toastgoand.home.clans

import android.app.PendingIntent.getActivity
import android.content.Intent
import android.os.Build
import android.text.style.ClickableSpan
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import coil.compose.rememberImagePainter
import com.example.toastgoand.auth.strangerintro.StrangerIntroActivity
import com.example.toastgoand.home.LandingActivity
import com.example.toastgoand.home.clans.components.LiveClanItem
import com.example.toastgoand.home.clantalk.ClanTalkActivity
import com.example.toastgoand.network.myclans.MyClansDataClass
import com.example.toastgoand.utilities.drawColorShadow
import com.google.android.material.internal.ContextUtils.getActivity

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MyClanItem(myclan: MyClansDataClass) {
    if (myclan.ongoing_frame) {
        LiveClanItem(myclan = myclan)
    } else {
        DormantClan(myclan = myclan)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun DormantClan(myclan: MyClansDataClass) {
    val context = LocalContext.current

    Row (modifier = Modifier.clickable {
        context.startActivity(Intent(context, ClanTalkActivity::class.java).apply {
            putExtra("clubName", myclan.club_name)
            putExtra("clubid", myclan.club_id)
            putExtra("channelid", myclan.pn_channel_id)
            putExtra("ongoingFrame", myclan.ongoing_frame)
            putExtra("startTime", myclan.start_time)
            putExtra("endTime", myclan.end_time)
        })
    }) {
        ClanImage(myclan = myclan)
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(text = myclan.club_name, style = MaterialTheme.typography.subtitle1, color = Color.Black)
            Text(text = "tap to start new frame", style = MaterialTheme.typography.caption, color = Color.Blue)
        } 
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun ClanImage(myclan: MyClansDataClass) {
    val painter = rememberImagePainter(data = myclan.club_profile_pic)
    Image(
        painter = painter,
        contentDescription = "Forest Image",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(8.dp)
            .size(55.dp)
            .clip(RoundedCornerShape(corner = CornerSize(27.5.dp)))
            .drawColorShadow(Color.Blue)
    )
}
