package com.example.toastgoand.network.myfriends

import androidx.annotation.WorkerThread
import com.example.toastgoand.network.directs.MyDirectsDataClass
import kotlinx.coroutines.flow.Flow

class MyFriendsRepo (private val myFriendsDao: MyFriendsDao) {

    val myFriends: Flow<List<MyFriendsDataClass>> = myFriendsDao.getMyFriends()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(myFriends: List<MyFriendsDataClass>) {
        myFriendsDao.insert(myFriends = myFriends)
    }
}