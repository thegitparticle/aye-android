package com.example.toastgoand.network

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.toastgoand.network.defaultrecos.DefaultRecosDao
import com.example.toastgoand.network.defaultrecos.DefaultRecosDataClass
import com.example.toastgoand.network.directs.MyDirectsDao
import com.example.toastgoand.network.directs.MyDirectsDataClass
import com.example.toastgoand.network.myclans.MyClansDao
import com.example.toastgoand.network.myclans.MyClansDataClass
import com.example.toastgoand.network.myfriends.MyFriendsDao
import com.example.toastgoand.network.myfriends.MyFriendsDataClass
import com.example.toastgoand.network.nudgelist.NudgeToDao
import com.example.toastgoand.network.nudgelist.NudgeToDataClass
import com.example.toastgoand.network.userdetails.UserDetailsDao
import com.example.toastgoand.network.userdetails.UserDetailsDataClass
import kotlinx.coroutines.CoroutineScope

@Database(
    entities = [UserDetailsDataClass::class, MyClansDataClass::class, MyDirectsDataClass::class, NudgeToDataClass::class, MyFriendsDataClass::class, DefaultRecosDataClass::class],
    version = 2
)

@TypeConverters(ConverterGo::class)

abstract class AppRoomDB : RoomDatabase() {

    //list all DAOs
    abstract fun userDetailsDao(): UserDetailsDao
    abstract fun myClansDao(): MyClansDao
    abstract fun myDirectsDao(): MyDirectsDao
    abstract fun nudgeToDao(): NudgeToDao
    abstract fun myFriendsDao(): MyFriendsDao
    abstract fun defaultRecosDao(): DefaultRecosDao

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
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }

        private class AppRoomDBCallback(private val scope: CoroutineScope) :
            RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
            }
        }
    }
}