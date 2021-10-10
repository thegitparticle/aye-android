package com.example.toastgoand.home.clantalk.components

import android.util.Log
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.ui.layout.Row
import androidx.ui.layout.fillMaxWidth
import coil.compose.rememberImagePainter
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.home.otherprofile.DepthDetails
import com.example.toastgoand.home.otherprofile.OtherProfileDataClass
import com.example.toastgoand.home.otherprofile.network.OtherProfileApi
import com.example.toastgoand.network.userdetails.UserDetailsDataClass
import com.example.toastgoand.utilities.drawColorShadow
import kotlinx.coroutines.launch
import java.lang.Exception

//@Composable
//fun StreamGoingOnBar(streamRunnerId: Int) {
//    val composableScope = rememberCoroutineScope()
//
//    var streamerDetails: OtherProfileDataClass = OtherProfileDataClass(
//        user = DepthDetails(
//            username = "",
//            phone = "",
//            full_name = "",
//            id = 0,
//            clubs_joined_by_user = "",
//            number_of_clubs_joined = 0,
//            contact_list = "",
//            total_frames_participation = 0,
//            country_code_of_user = "",
//            contact_list_sync_status = false
//        ),
//        bio = "",
//        image = "",
//        id = 0
//    )
//
//
//    composableScope.launch {
//        try {
//            streamerDetails =
//                OtherProfileApi.retrofitService.getOtherProfile("", streamRunnerId.toString())[0]
//        } catch (e: Exception) {
//            Log.i("clanstalk other user deets", e.toString())
//        }
//    }
//
//    AyeTheme() {
//        androidx.compose.foundation.layout.Row(
//            modifier = Modifier
//                .fillMaxWidth(0.9f)
//                .padding(vertical = 10.dp)
//                .background(color = AyeTheme.colors.brandVariant),
//            horizontalArrangement = Arrangement.SpaceBetween,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Row(verticalAlignment = Alignment.CenterVertically) {
//                val painter = rememberImagePainter(data = streamerDetails.image)
//                Image(
//                    painter = painter,
//                    contentDescription = "stream dp in clantalk",
//                    contentScale = ContentScale.Crop,
//                    modifier = Modifier
//                        .padding(horizontal = 20.dp)
//                        .size(40.dp)
//                        .clip(RoundedCornerShape(corner = CornerSize(20.dp)))
//                )
//                Text(
//                    text = "${streamerDetails.user.full_name} is streaming now",
//                    style = MaterialTheme.typography.subtitle2,
//                    color = AyeTheme.colors.textPrimary,
//                    modifier = Modifier.padding(horizontal = 20.dp)
//                )
//            }
//            Row(verticalAlignment = Alignment.CenterVertically) {
//                Box(
//                    modifier = Modifier
//                        .wrapContentWidth()
//                        .clip(RoundedCornerShape(20.dp))
//                        .background(Color.White)
//                        .clickable {  }
//                        .width(75.dp)
//                        .height(40.dp),
//                    contentAlignment = Alignment.Center
//                ) {
//                    Text(
//                        text = "join",
//                        style = MaterialTheme.typography.caption,
//                        color = Color.Gray
//                    )
//                }
//            }
//        }
//    }
//}
//}