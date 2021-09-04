package com.example.toastgoand.prefhelpers

import android.content.Context
import android.content.SharedPreferences

class PrefHelper (context: Context) {

    private val PREFSNAME = "sharedprefauth"
    private var sharedPref: SharedPreferences = context.getSharedPreferences(PREFSNAME, Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = sharedPref.edit()

    fun put(key: String, value: String) {
        editor.putString(key, value)
            .apply()
    }

    fun put(key: String, value: Boolean) {
        editor.putBoolean(key, value)
            .apply()
    }

    fun getBoolean(key: String): Boolean {
        return sharedPref.getBoolean(key, false)
    }

    fun getString(key: String): String? {
        return sharedPref.getString(key, null)
    }

    fun clear() {
        editor.clear()
            .apply()
    }

}