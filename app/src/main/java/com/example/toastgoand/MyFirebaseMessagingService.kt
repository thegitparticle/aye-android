package com.example.toastgoand

import android.content.Context
import android.content.SharedPreferences
import com.example.toastgoand.prefhelpers.Constant
import com.google.firebase.messaging.FirebaseMessagingService

class MyFirebaseMessagingService(context: Context): FirebaseMessagingService() {

    private val PREFSNAME = "sharedprefauth"
    private var sharedPref: SharedPreferences = context.getSharedPreferences(PREFSNAME, Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = sharedPref.edit()

    override fun onNewToken(token: String) {

        val oldToken = sharedPref.getString(Constant.FIREBASE_TOKEN, null)

        if (token == oldToken) {
            return
        }

//        updatePushNotificationsOnChannels(
//            sharedPref.getString()
//        )
    }

}