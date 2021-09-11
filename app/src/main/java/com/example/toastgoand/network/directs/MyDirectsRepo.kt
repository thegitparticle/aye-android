package com.example.toastgoand.network.directs

import androidx.annotation.WorkerThread
import com.example.toastgoand.network.myclans.MyClansDataClass
import kotlinx.coroutines.flow.Flow

class MyDirectsRepo (private val myDirectsDao: MyDirectsDao) {

    val myDirects: Flow<List<MyDirectsDataClass>> = myDirectsDao.getMyDirects()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(myDirects: List<MyDirectsDataClass>) {
        myDirectsDao.insert(myDirects = myDirects)
    }

}