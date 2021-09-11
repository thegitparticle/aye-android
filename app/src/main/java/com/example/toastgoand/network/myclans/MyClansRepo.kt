package com.example.toastgoand.network.myclans

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class MyClansRepo (private val myClansDao: MyClansDao) {

    val myClans: Flow<List<MyClansDataClass>> = myClansDao.getMyClans()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(myClans: List<MyClansDataClass>) {
        myClansDao.insert(myClans = myClans)
    }

}