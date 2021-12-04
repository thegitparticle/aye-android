package com.example.toastgoand

import android.app.Application
import android.util.Log
import com.androidnetworking.AndroidNetworking
import com.example.toastgoand.network.AppRoomDB
import com.example.toastgoand.network.defaultrecos.DefaultRecosRepo
import com.example.toastgoand.network.directs.MyDirectsRepo
import com.example.toastgoand.network.myclans.MyClansRepo
import com.example.toastgoand.network.myfriends.MyFriendsRepo
import com.example.toastgoand.network.nudgelist.NudgeToRepo
import com.example.toastgoand.network.userdetails.UserDetailsRepo
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import net.gotev.uploadservice.UploadServiceConfig

@HiltAndroidApp
class ToastgoApplication: Application() {
    val applicationScope = CoroutineScope(SupervisorJob())

    override fun onCreate() {

        super.onCreate()

        AndroidNetworking.initialize(this)

//        UploadServiceConfig.initialize(
//            context = this,
//            debug = BuildConfig.DEBUG
//        )
    }

//
//    fun logHere () {
//        Log.i("slowstartupdebug", "before database initlaiza in app class")
//    }
//    fun logHere2 () {
//        Log.i("slowstartupdebug", "after all database initlaiza in app class")
//    }
//    // Using by lazy so the database and the repository are only created when they're needed
//    // rather than when the application starts
    val database by lazy { AppRoomDB.getDatabase(this, applicationScope) }
    val repository by lazy { UserDetailsRepo(database.userDetailsDao()) }
    val repositoryMyClans by lazy { MyClansRepo(database.myClansDao()) }
    val repositoryMyDirects by lazy {MyDirectsRepo(database.myDirectsDao())}
    val repositoryNudgeTo by lazy {NudgeToRepo(database.nudgeToDao())}
    val repositoryMyFriends by lazy {MyFriendsRepo(database.myFriendsDao())}
    val repositoryDefaultRecos by lazy {DefaultRecosRepo(database.defaultRecosDao())}
}