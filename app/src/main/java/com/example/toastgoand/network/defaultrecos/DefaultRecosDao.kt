package com.example.toastgoand.network.defaultrecos

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Entity(
    tableName = "defaultRecosTable"
)

@Dao
interface DefaultRecosDao {
    @Query("SELECT * FROM defaultRecosTable")
    fun getDefaultRecos(): Flow<List<DefaultRecosDataClass>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(defaultRecos: List<DefaultRecosDataClass>)

    @Query("DELETE FROM defaultRecosTable")
    suspend fun deleteAll()
}