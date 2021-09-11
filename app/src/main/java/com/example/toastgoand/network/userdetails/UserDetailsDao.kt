package com.example.toastgoand.network.userdetails

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.Serializable

@Dao
interface UserDetailsDao {

    @Query("SELECT * FROM userDetailsTable")
    fun getAllData(): Flow<UserDetailsDataClass>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userDetails: UserDetailsDataClass)

    @Query("DELETE FROM userDetailsTable")
    suspend fun deleteAll()

}