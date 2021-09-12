package com.example.toastgoand.network.nudgelist

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface NudgeToDao {
    @Query("SELECT * FROM nudgeToTable")
    fun getNudgeTo(): Flow<List<NudgeToDataClass>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(nudgeTo: List<NudgeToDataClass>)

    @Query("DELETE FROM nudgeToTable")
    suspend fun deleteAll()
}