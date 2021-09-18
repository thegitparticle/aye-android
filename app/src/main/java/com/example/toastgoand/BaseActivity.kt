package com.example.toastgoand

import android.content.ContentValues.TAG
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.example.toastgoand.prefhelpers.Constant
import com.example.toastgoand.prefhelpers.PrefHelper
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging

abstract class BaseActivity: AppCompatActivity() {
    open lateinit var viewBinding: ViewBinding
    abstract fun binding(): ViewBinding

//    open lateinit var prefHelper: PrefHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = binding()
        setContentView(viewBinding.root)

        getSupportActionBar()?.hide()

        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(TAG, "Firebase Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }

            val token = task.result

            Log.d(TAG, "Firebase token: " + token)
//
//            prefHelper = PrefHelper(this)
//            if (token != null) {
//                prefHelper.put( Constant.FIREBASE_TOKEN , token)
//            }

        })

    }

}