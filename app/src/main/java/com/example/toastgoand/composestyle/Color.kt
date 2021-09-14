package com.example.toastgoand.composestyle

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
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

val typography = Typography(
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
        fontSize = 11.sp
    ),
)

private val DarkColors = darkColors(
    primary = Color(0xFFF0421F),
    primaryVariant = Color(0xFFFF774C),
    secondary = Color(0xFF1ABCFE),
    secondaryVariant = Color(0xFF008CCB),
    background = Color(0xFFFFFFFF),
    surface = Color(0xFFF5FAFC),
    error = Color(0xFFF32013),
    onPrimary = Color(0xFFFFFFFF),
    onSecondary = Color(0xFFFFFFFF),
    onBackground = Color(0xFF15161A),
    onSurface = Color(0xFF15161A),
    onError = Color(0xFFFFFFFF)
)

private val LightColors = lightColors(
    primary = Color(0xFFF0421F),
    primaryVariant = Color(0xFFFF774C),
    secondary = Color(0xFF1ABCFE),
    secondaryVariant = Color(0xFF008CCB),
    background = Color(0xFFFFFFFF),
    surface = Color(0xFFF7F8FA),
    error = Color(0xFFF32013),
    onPrimary = Color(0xFFFFFFFF),
    onSecondary = Color(0xFFFFFFFF),
    onBackground = Color(0xFF15161A),
    onSurface = Color(0xFF15161A),
    onError = Color(0xFFFFFFFF)
)

@Composable
fun AyeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColors
    } else {
        LightColors
    }

    MaterialTheme(
        colors = colors,
        typography = typography,
        content = content
    )
}
