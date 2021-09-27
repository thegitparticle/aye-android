package com.example.toastgoand.network.nudgelist

import androidx.annotation.WorkerThread
import com.example.toastgoand.network.directs.MyDirectsDataClass
import kotlinx.coroutines.flow.Flow

class NudgeToRepo (private val nudgeToDao: NudgeToDao) {

    val nudgeTo: Flow<List<NudgeToDataClass>> = nudgeToDao.getNudgeTo()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(nudgeTo: List<NudgeToDataClass>) {
        nudgeToDao.insert(nudgeTo = nudgeTo)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteAll() {
        nudgeToDao.deleteAll()
    }
}