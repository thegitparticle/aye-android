package com.example.toastgoand.home.directs.components

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.home.directs.network.NudgeToStartDirectApi
import com.example.toastgoand.network.nudgelist.NudgeToDataClass
import com.example.toastgoand.utilities.drawColorShadow
import kotlinx.coroutines.launch

@Composable
fun NudgeToItem(nudgeItem: NudgeToDataClass, currentuserid: String) {
    AyeTheme() {
        Row(
            modifier = Modifier
                .padding(horizontal = 15.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                NudgeToImage(nudgeItem = nudgeItem)

                    Text(
                        text = nudgeItem.name,
                        style = MaterialTheme.typography.subtitle1,
                        color = AyeTheme.colors.textPrimary,
                        modifier = Modifier
                            .padding(horizontal = 16.dp),
                    )
            }
            StartButton(nudgeItem = nudgeItem, currentuserid = currentuserid)
        }
    }
}

@Composable
private fun StartButton(nudgeItem: NudgeToDataClass, currentuserid: String) {
    val composableScope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .wrapContentWidth()
            .clip(RoundedCornerShape(15.dp))
            .background(AyeTheme.colors.success.copy(.08f))
            .clickable {
                composableScope.launch {
                    try {
                        NudgeToStartDirectApi.retrofitService.startANewDirect(
                            mainuserid = currentuserid,
                            otheruserid = nudgeItem.id.toString(),
                            directid = currentuserid + "_" + nudgeItem.id.toString() + "_" + "d"
                        )
                    } catch (e: Exception) {
                        Log.i("startdirect", e.toString())
                    }
                }
            }
            .width(75.dp)
            .height(30.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "start",
            style = MaterialTheme.typography.subtitle2,
            color = AyeTheme.colors.success
        )
    }
}

@Composable
private fun NudgeToImage(nudgeItem: NudgeToDataClass) {
    val painter = rememberImagePainter(data = nudgeItem.profile_pic)
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
                .drawColorShadow(MaterialTheme.colors.onBackground, offsetY = 4.dp, alpha = 0.5f)
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