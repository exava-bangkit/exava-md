package com.exava.exava.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.exava.exava.data.database.dao.TourismDao

@Database(entities = [], version = 1)
abstract class TourismDatabase: RoomDatabase() {

    abstract val tourismDao: TourismDao

    companion object {
        @Volatile
        private var instance: TourismDatabase? = null

        @JvmStatic
        fun getInstance(context: Context): TourismDatabase {
            return instance ?: synchronized(this) {
                instance = Room.databaseBuilder(context, TourismDatabase::class.java, "tourism").build()
                return requireNotNull(instance)
            }
        }
    }
}