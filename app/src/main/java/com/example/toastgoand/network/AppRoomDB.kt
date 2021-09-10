package com.example.toastgoand.network

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.sqlite.db.SupportSQLiteQuery
import com.example.toastgoand.network.userdetails.UserDetailsDao
import com.example.toastgoand.network.userdetails.UserDetailsDataClass
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [UserDetailsDataClass::class], version = 1)

@TypeConverters(ConverterGo::class)

abstract class AppRoomDB : RoomDatabase() {

    //list all DAOs
    abstract fun userDetailsDao(): UserDetailsDao

    companion object {
        @Volatile
        private var INSTANCE: AppRoomDB? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): AppRoomDB {
            // if the INSTANCE is not null, then return it, if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppRoomDB::class.java,
                    "database"
                )
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

        private class AppRoomDBCallback (private val scope: CoroutineScope) : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
            }
        }
    }
}