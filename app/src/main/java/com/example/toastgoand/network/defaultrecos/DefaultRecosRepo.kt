package com.example.toastgoand.network.defaultrecos

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class DefaultRecosRepo (private val defaultRecosDao: DefaultRecosDao) {

    val defaultRecos: Flow<List<DefaultRecosDataClass>> = defaultRecosDao.getDefaultRecos()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(defaultRecos: List<DefaultRecosDataClass>) {
        defaultRecosDao.insert(defaultRecos = defaultRecos)
    }

}