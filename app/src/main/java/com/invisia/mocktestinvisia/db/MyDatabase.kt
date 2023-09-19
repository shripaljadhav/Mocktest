package com.invisia.mocktestinvisia.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [PersonEntity::class, GuestEntity::class, ChildEntity::class], version = 1)
@TypeConverters(Converters::class) // Add this line to specify type converters
abstract class MyDatabase : RoomDatabase() {

    abstract fun myDao(): MyDao // Provides access to the DAO interface

    companion object {
        @Volatile
        private var INSTANCE: MyDatabase? = null

        // Singleton pattern to ensure there's only one database instance
        fun getInstance(context: Context): MyDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyDatabase::class.java,
                    "my_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}



