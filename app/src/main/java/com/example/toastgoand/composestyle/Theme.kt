package com.example.toastgoand.composestyle

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
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

val Rose3 = Color(0xfff985aa)
val Ocean3 = Color(0xff86f7fa)

private val DarkColors = TheAyeColors(
    brand = Color(0xFFF0421F),
    brandVariant = Color(0xFFFF774C),
    appLead = Color(0xFF1ABCFE),
    appLeadVariant = Color(0xFF008CCB),
    uiBackground = Color(0xFFFFFFFF),
    uiSurface = Color(0xFFF5FAFC),
    error = Color(0xFFF32013),
    textPrimary = Color(0xFFFFFFFF),
    textSecondary = Color(0xFFFFFFFF),
    textSpecial = Color(0xFF15161A),
    textLink = Color(0xFF0ACF83),
    iconBackground = Color(0xFFFFFFFF),
    iconVector = Color(0xFFFFFFFF),
    success = Color(0xFFFFFFFF),
    notification = Color(0xFFFFFFFF),
    gradient1_1 = listOf(Rose3, Ocean3),
    isDark = true
)

private val LightColors = TheAyeColors(
    brand = Color(0xFFF0421F),
    brandVariant = Color(0xFFFF774C),
    appLead = Color(0xFF1ABCFE),
    appLeadVariant = Color(0xFF008CCB),
    uiBackground = Color(0xFFFFFFFF),
    uiSurface = Color(0xFFF7F8FA),
    error = Color(0xFFF32013),
    textPrimary = Color(0xFFFFFFFF),
    textSecondary = Color(0xFFFFFFFF),
    textSpecial = Color(0xFF15161A),
    textLink = Color(0xFF15161A),
    iconBackground = Color(0xFFFFFFFF),
    iconVector = Color(0xFFFFFFFF),
    success = Color(0xFFFFFFFF),
    notification = Color(0xFFFFFFFF),
    gradient1_1 = listOf(Rose3, Ocean3),
    isDark = false
)

@Composable
fun ProvideAyeColors(
    colorsHere: TheAyeColors,
    content: @Composable () -> Unit
) {
    val colorPalette = remember {
        // Explicitly creating a new object here so we don't mutate the initial [colors]
        // provided, and overwrite the values set in it.
        colorsHere.copy()
    }
    colorPalette.update(colorsHere)
    CompositionLocalProvider(LocalAyeColors provides colorPalette, content = content)
}

private val LocalAyeColors = staticCompositionLocalOf<TheAyeColors> {
    error("No JetsnackColorPalette provided")
}

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

    ProvideAyeColors(colorsHere = colors) {
        MaterialTheme(
            colors = debugColors(darkTheme),
            typography = typography,
            content = content
        )
    }
}

fun debugColors(
    darkTheme: Boolean,
    debugColor: Color = Color.Magenta
) = Colors(
    primary = debugColor,
    primaryVariant = debugColor,
    secondary = debugColor,
    secondaryVariant = debugColor,
    background = debugColor,
    surface = debugColor,
    error = debugColor,
    onPrimary = debugColor,
    onSecondary = debugColor,
    onBackground = debugColor,
    onSurface = debugColor,
    onError = debugColor,
    isLight = !darkTheme
)
