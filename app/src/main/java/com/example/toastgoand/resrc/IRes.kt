package com.example.toastgoand.resrc

import android.graphics.drawable.Drawable

interface IRes {
    fun str(resId: Int): String
    fun color(resId: Int): Int
    fun drawable(resId: Int): Drawable?
}