package com.example.coolishhelperapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.coolishhelperapp.data.model.GroceryTask
import kotlin.concurrent.Volatile


@Database(entities = [GroceryTask::class] , version = 1 , exportSchema = false)
abstract class GroceriesLocalDatabase : RoomDatabase() {

    abstract fun groceryDao() : GroceryDao

    companion object {
        @Volatile
        private var Instance: GroceriesLocalDatabase? = null

        fun getDatabase(context: Context): GroceriesLocalDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, GroceriesLocalDatabase::class.java, "grocery_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}