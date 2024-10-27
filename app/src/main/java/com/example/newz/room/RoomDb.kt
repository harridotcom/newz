package com.example.newz.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FavoriteNews::class], version = 3, exportSchema = false)
abstract class RoomDb : RoomDatabase() {
    abstract fun getnewsDao(): NewsDao

    companion object {
        @Volatile
        private var INSTANCE: RoomDb? = null

        fun getDatabase(context: Context): RoomDb {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoomDb::class.java,
                    "saved_news_database"
                )
                    .fallbackToDestructiveMigration()  // Add this line
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
