package com.example.toastgoand.network.myfriends

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MyFriendsDao {
    @Query("SELECT * FROM myFriendsTable")
    fun getMyFriends(): Flow<List<MyFriendsDataClass>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(myFriends: List<MyFriendsDataClass>)

    @Query("DELETE FROM myFriendsTable")
    suspend fun deleteAll()
}