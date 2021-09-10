package com.example.toastgoand.network.userdetails

import android.accounts.Account
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.sqlite.db.SimpleSQLiteQuery
import kotlinx.coroutines.flow.Flow

class UserDetailsRepo(private val userDetailsDao: UserDetailsDao) {

    val userDetails: Flow<UserDetailsDataClass> = userDetailsDao.getAllData()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(userDetails: UserDetailsDataClass) {
        userDetailsDao.insert(userDetails = userDetails)
    }
}