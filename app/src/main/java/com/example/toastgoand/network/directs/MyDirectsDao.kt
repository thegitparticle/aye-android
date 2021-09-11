package com.example.toastgoand.network.directs

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MyDirectsDao {
    @Query("SELECT * FROM myDirectsTable")
    fun getMyDirects(): Flow<List<MyDirectsDataClass>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(myDirects: List<MyDirectsDataClass>)

    @Query("DELETE FROM myDirectsTable")
    suspend fun deleteAll()
}