package com.example.toastgoand.network.userdetails

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDetailsDao {

    @Query("SELECT * FROM userDetailsTable")
    fun getAllData(): Flow<UserDetailsDataClass>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userDetails: UserDetailsDataClass)

    @Query("DELETE FROM userDetailsTable")
    suspend fun deleteAll()

}