package com.example.toastgoand

import android.app.Application
import com.example.toastgoand.network.AppRoomDB
import com.example.toastgoand.network.userdetails.UserDetailsRepo
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

@HiltAndroidApp
class ToastgoApplication: Application() {
    val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { AppRoomDB.getDatabase(this, applicationScope) }
    val repository by lazy { UserDetailsRepo(database.userDetailsDao()) }
}