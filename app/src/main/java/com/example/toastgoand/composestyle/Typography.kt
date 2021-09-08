package com.example.toastgoand.composestyle

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.material.Typography
import com.example.toastgoand.R

val fonts = FontFamily(
    Font(R.font.gotham_rounded_bold, weight = FontWeight.Bold),
    Font(R.font.gotham_rounded_bolditalic, weight = FontWeight.Bold, style= FontStyle.Italic),
    Font(R.font.gotham_rounded_medium, weight = FontWeight.Medium),
    Font(R.font.gotham_rounded_mediumitalic, weight = FontWeight.Medium, style= FontStyle.Italic),
    Font(R.font.gotham_rounded_book, weight = FontWeight.Normal),
    Font(R.font.gotham_rounded_bookitalic, weight = FontWeight.Normal, style= FontStyle.Italic),
    Font(R.font.gotham_rounded_light, weight = FontWeight.Light),
    Font(R.font.gotham_rounded_lightitalic, weight = FontWeight.Light, style= FontStyle.Italic),
)

val Typography = Typography(
   h1 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Bold,
        fontSize = 34.sp
   ),
    h2 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp
    ),
    h3 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp
    ),
    h4 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    h5 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Medium,
        fontSize = 17.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Medium,
        fontSize = 15.sp
    ),
    body1 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp
    ),
    subtitle2 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Bold,
        fontSize = 13.sp
    ),
    body2 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp
    ),
    caption = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    ),
)