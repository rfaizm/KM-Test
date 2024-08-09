package com.example.testapp.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.testapp.data.api.response.DataItem

@Database(
    entities = [DataItem::class],
    version = 1,
    exportSchema = false
)
abstract class UsernameDatabase : RoomDatabase() {

    companion object {
        @Volatile
        private var INSTANCE: UsernameDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): UsernameDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    UsernameDatabase::class.java, "quote_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}