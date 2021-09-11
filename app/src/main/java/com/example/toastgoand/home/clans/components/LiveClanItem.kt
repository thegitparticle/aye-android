package com.example.toastgoand.home.clans.components

import android.content.res.AssetManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layout
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.toastgoand.home.clans.DisplayPhoto
import com.example.toastgoand.home.clans.MyClanDataClass
import com.example.toastgoand.network.myclans.DisplayPhotos
import com.example.toastgoand.network.myclans.MyClansDataClass
import com.example.toastgoand.utilities.drawColorShadow
import com.google.accompanist.appcompattheme.AppCompatTheme
import com.google.android.material.composethemeadapter.MdcTheme

@Composable
fun LiveClanItem(myclan: MyClansDataClass) {
    val totalMember: Int? = myclan.display_photos?.size

    AppCompatTheme() {
        Column() {
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

@Composable
fun OnePerson(displayObject: DisplayPhotos) {
    AppCompatTheme() {
        PersonImage(displayObject.display_pic)
    }
}

@Composable
fun TwoPeople(displayItems: List<DisplayPhotos>) {
    AppCompatTheme() {
        Row() {
           PersonImage(imageLink = displayItems[0].display_pic)
            PersonImage(imageLink = displayItems[1].display_pic)
        }

    }
}

@Composable
fun ThreePeople(displayItems: List<DisplayPhotos>) {
    AppCompatTheme() {
        Column() {
            PersonImage(imageLink = displayItems[0].display_pic)
            Row() {
                PersonImage(imageLink = displayItems[1].display_pic)
                PersonImage(imageLink = displayItems[2].display_pic)
            }
        }
    }
}

@Composable
fun ThreePlusPeople(displayItems: List<DisplayPhotos>) {
    AppCompatTheme() {
        Column() {
            PersonImage(imageLink = displayItems[0].display_pic)
            Row() {
                PersonImage(imageLink = displayItems[1].display_pic)
                PersonImage(imageLink = displayItems[2].display_pic)
            }
            PlusPeople(displayItems = displayItems)
        }
    }
}

@Composable
fun TextPieces(myclan: MyClansDataClass) {
    AppCompatTheme() {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = myclan.club_name, style = MaterialTheme.typography.subtitle1, color = Color.Black)
            Text(text = "new frames", style = MaterialTheme.typography.caption, color = Color.Blue)
        }
        
    }
}

@Composable
private fun PersonImage(imageLink: String) {
    val painter = rememberImagePainter(data = imageLink)
    Image(
        painter = painter,
        contentDescription = "clan member dp",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .padding(8.dp)
            .size(60.dp)
            .clip(RoundedCornerShape(corner = CornerSize(30.dp)))
            .border(3.dp, Color.Red)
    )
}

@Composable
private fun PlusPeople(displayItems: List<DisplayPhotos>) {
    val extraNumber = displayItems.size - 3

    Box(contentAlignment= Alignment.Center,
        modifier = Modifier
            .background(Color.Black, shape = CircleShape)
            .layout() { measurable, constraints ->
                // Measure the composable
                val placeable = measurable.measure(constraints)

                //get the current max dimension to assign width=height
                val currentHeight = placeable.height
                var heightCircle = currentHeight
                if (placeable.width > heightCircle)
                    heightCircle = placeable.width

                //assign the dimension and the center position
                layout(heightCircle, heightCircle) {
                    // Where the composable gets placed
                    placeable.placeRelative(0, (heightCircle - currentHeight) / 2)
                }
            }) {

        Text(
            text = "+ ${extraNumber}",
            textAlign = TextAlign.Center,
            color = Color.White,
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier
                .padding(4.dp)
                .defaultMinSize(15.dp) //Use a min size for short text.
        )
    }
}