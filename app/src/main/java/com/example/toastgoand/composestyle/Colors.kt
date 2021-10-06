package com.example.toastgoand.composestyle

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

class TheAyeColors(
    brand: Color,
    brandVariant: Color,
    appLead: Color,
    appLeadVariant: Color,
    success: Color,
    error: Color,
    notification: Color,
    uiBackground: Color,
    uiSurface: Color,
    textPrimary: Color,
    textSecondary: Color,
    textLink: Color,
    iconBackground: Color,
    iconVector: Color,
    textSpecial: Color,
    special1: Color,
    special2: Color,
    special3: Color,
    special4: Color,
    gradient1_1: List<Color>,
    isDark: Boolean,
) {
    var gradient1_1 by mutableStateOf(gradient1_1)
        private set
    var brand by mutableStateOf(brand)
        private set
    var brandVariant by mutableStateOf(brandVariant)
        private set
    var appLead by mutableStateOf(appLead)
        private set
    var appLeadVariant by mutableStateOf(appLeadVariant)
        private set
    var uiBackground by mutableStateOf(uiBackground)
        private set
    var uiSurface by mutableStateOf(uiSurface)
        private set
    var textPrimary by mutableStateOf(textPrimary)
        private set
    var textSecondary by mutableStateOf(textSecondary)
        private set
    var textSpecial by mutableStateOf(textSpecial)
        private set
    var textLink by mutableStateOf(textLink)
        private set
    var iconBackground by mutableStateOf(iconBackground)
        private set
    var iconVector by mutableStateOf(iconVector)
        private set
    var success by mutableStateOf(success)
        private set
    var error by mutableStateOf(error)
        private set
    var notification by mutableStateOf(notification)
        private set
    var special1 by mutableStateOf(special1)
        private set
    var special2 by mutableStateOf(special2)
        private set
    var special3 by mutableStateOf(special3)
        private set
    var special4 by mutableStateOf(special4)
        private set
    var isDark by mutableStateOf(isDark)
        private set

    fun update(other: TheAyeColors) {
        gradient1_1 = other.gradient1_1
        brand = other.brand
        brandVariant = other.brandVariant
        appLead = other.appLead
        appLeadVariant = other.appLeadVariant
        uiBackground = other.uiBackground
        uiSurface = other.uiSurface
        textPrimary = other.textPrimary
        textSecondary = other.textSecondary
        textSpecial = other.textSpecial
        textLink = other.textLink
        iconBackground = other.iconBackground
        iconVector = other.iconVector
        success = other.success
        error = other.error
        notification = other.notification
        isDark = other.isDark
        special1 = other.special1
        special2 = other.special2
        special3 = other.special3
        special4 = other.special4
    }

    fun copy(): TheAyeColors = TheAyeColors(
        gradient1_1 = gradient1_1,
        brand = brand,
        brandVariant = brandVariant,
        appLead = appLead,
        appLeadVariant = appLeadVariant,
        uiBackground = uiBackground,
        uiSurface = uiSurface,
        textPrimary = textPrimary,
        textSecondary = textSecondary,
        textSpecial = textSpecial,
        textLink = textLink,
        iconBackground = iconBackground,
        iconVector = iconVector,
        success = success,
        error = error,
        notification = notification,
        isDark = isDark,
        special1 = special1,
        special2 = special2,
        special3 = special3,
        special4 = special4,
    )
}
