package com.example.toastgoand.navigator

import android.view.View

enum class Screen {

    LOGIN,
    LANDING
}

interface Navigator {
    fun navigateTo(screen: Screen)
}