package com.example.toastgoand.home.clans.components

import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DynamicFeed
import androidx.compose.material.icons.outlined.Layers
import androidx.compose.material.icons.rounded.Layers
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.home.clantalk.ClanTalkActivity
import com.example.toastgoand.network.myclans.DisplayPhotos
import com.example.toastgoand.network.myclans.MyClansDataClass
import com.example.toastgoand.utilities.drawColorShadow
import compose.icons.FeatherIcons
import compose.icons.feathericons.Layers

@Composable
fun LiveClanItem(myclan: MyClansDataClass, position: Int) {
    val context = LocalContext.current
    val totalMember: Int? = myclan.display_photos?.size

    AyeTheme() {
        if (position % 2 == 0) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 7.5.dp, horizontal = 5.dp)
                    .clickable {
                        context.startActivity(Intent(context, ClanTalkActivity::class.java).apply {
                            putExtra("clubName", myclan.club_name)
                            putExtra("clubid", myclan.club_id)
                            putExtra("channelid", myclan.pn_channel_id)
                            putExtra("ongoingFrame", myclan.ongoing_frame)
                            putExtra("startTime", myclan.start_time)
                            putExtra("endTime", myclan.end_time)
                            putExtra("directornot", false)
                        })
                    },
                horizontalArrangement = Arrangement.Start
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth(fraction = 0.4f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (totalMember == 2) {
                        OnePerson(displayObject = myclan.display_photos?.get(0))
                    } else if (totalMember == 3) {
                        TwoPeople(displayItems = myclan.display_photos)
                    } else if (totalMember == 4) {
                        ThreePeople(displayItems = myclan.display_photos)
                    } else if (totalMember != null) {
                        if (totalMember > 4) {
                            myclan.display_photos?.let { ThreePlusPeople(displayItems = it) }
                        } else {
                            OnePerson(displayObject = myclan.display_photos?.get(0))
                        }
                    }
                    TextPieces(myclan = myclan)
                }
            }
        } else {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 7.5.dp)
                    .clickable {
                        context.startActivity(Intent(context, ClanTalkActivity::class.java).apply {
                            putExtra("clubName", myclan.club_name)
                            putExtra("clubid", myclan.club_id)
                            putExtra("channelid", myclan.pn_channel_id)
                            putExtra("ongoingFrame", myclan.ongoing_frame)
                            putExtra("startTime", myclan.start_time)
                            putExtra("endTime", myclan.end_time)
                        })
                    },
                horizontalArrangement = Arrangement.End
            ) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .fillMaxWidth(fraction = 0.4f)
                        .padding(vertical = 5.dp) ,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (totalMember == 2) {
                        OnePerson(displayObject = myclan.display_photos?.get(0))
                    } else if (totalMember == 3) {
                        TwoPeople(displayItems = myclan.display_photos)
                    } else if (totalMember == 4) {
                        ThreePeople(displayItems = myclan.display_photos)
                    } else if (totalMember != null) {
                        if (totalMember > 4) {
                            myclan.display_photos?.let { ThreePlusPeople(displayItems = it) }
                        } else {
                            OnePerson(displayObject = myclan.display_photos?.get(0))
                        }
                    }
                    TextPieces(myclan = myclan)
                }
            }
        }
    }
}

@Composable
fun OnePerson(displayObject: DisplayPhotos) {
    AyeTheme() {
        Row(Modifier.fillMaxSize(fraction = 0.4f), horizontalArrangement = Arrangement.Center) {
            PersonImage(displayObject.display_pic)
        }
    }
}

@Composable
fun TwoPeople(displayItems: List<DisplayPhotos>) {
    AyeTheme() {
        Row(Modifier.fillMaxSize(fraction = 0.4f), horizontalArrangement = Arrangement.Center) {
            PersonImage(imageLink = displayItems[0].display_pic)
            PersonImage(imageLink = displayItems[1].display_pic)
        }

    }
}

@Composable
fun ThreePeople(displayItems: List<DisplayPhotos>) {
    AyeTheme() {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            PersonImage(imageLink = displayItems[0].display_pic)
            Row(Modifier.fillMaxSize(), horizontalArrangement = Arrangement.Center) {
                PersonImage(imageLink = displayItems[1].display_pic)
                PersonImage(imageLink = displayItems[2].display_pic)
            }
        }
    }
}

@Composable
fun ThreePlusPeople(displayItems: List<DisplayPhotos>) {
    AyeTheme() {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            PersonImage(imageLink = displayItems[0].display_pic)
            Row(Modifier.fillMaxSize(), horizontalArrangement = Arrangement.Center) {
                PersonImage(imageLink = displayItems[1].display_pic)
                PersonImage(imageLink = displayItems[2].display_pic)
            }
            PlusPeople(displayItems = displayItems)
        }
    }
}

@Composable
fun TextPieces(myclan: MyClansDataClass) {
    AyeTheme() {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = myclan.club_name,
                style = MaterialTheme.typography.subtitle1,
                color = AyeTheme.colors.textPrimary
            )
            Row(modifier = Modifier.padding(vertical = 4.dp), verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    Icons.Rounded.Layers,
                    "Back",
                    tint = AyeTheme.colors.appLead,
                    modifier = Modifier.size(11.dp)
                )
                Text(
                    text = "live frame",
                    style = MaterialTheme.typography.caption,
                    color = AyeTheme.colors.appLead,
                    modifier = Modifier.padding(horizontal = 4.dp)
                )
            }
        }

    }
}

@Composable
private fun PersonImage(imageLink: String) {
    val painter = rememberImagePainter(data = imageLink)

    val infiniteTransition = rememberInfiniteTransition()

    val sizeofImage by infiniteTransition.animateFloat(
        initialValue = 0.6f,
        targetValue = 0.7f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    @RequiresApi(Build.VERSION_CODES.O)
    @Composable
    fun PersonDpBox(shape: Shape) {
        Column(
            modifier = Modifier
                .wrapContentSize(Alignment.Center)
        ) {
            Box(
                modifier = Modifier
                    .size((100 * sizeofImage).dp)
                    .clip(shape)
                    .background(MaterialTheme.colors.onSurface),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painter,
                    contentDescription = "clan member dp",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(70.dp).drawColorShadow(AyeTheme.colors.textSecondary, offsetY = 4.dp, alpha = 0.5f)
                )
            }
        }
    }

    PersonDpBox(shape = CircleShape)
}

@Composable
private fun PlusPeople(displayItems: List<DisplayPhotos>) {
    val extraNumber = displayItems.size - 3

    @Composable
    fun ExtraNumberBox(shape: Shape) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.Center)
        ) {
            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(shape)
                    .background(MaterialTheme.colors.onSurface),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "+${extraNumber}",
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    style = MaterialTheme.typography.subtitle1,
                    modifier = Modifier
                        .padding(4.dp)
                        .defaultMinSize(15.dp) //Use a min size for short text.
                )
            }
        }
    }

    ExtraNumberBox(shape = CircleShape)
}