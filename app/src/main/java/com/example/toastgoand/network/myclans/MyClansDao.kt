package com.example.toastgoand.network.myclans

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MyClansDao {

    @Query("SELECT * FROM myClansTable")
    fun getMyClans(): Flow<List<MyClansDataClass>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(myClans: List<MyClansDataClass>)

    @Query("DELETE FROM myClansTable")
    suspend fun deleteAll()
}