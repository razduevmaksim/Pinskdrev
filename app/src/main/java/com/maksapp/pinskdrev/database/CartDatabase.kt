package com.maksapp.pinskdrev.database

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(version = 2, entities = [CartItem::class])
abstract class CartDatabase : RoomDatabase() {
    abstract fun cartDao(): CartDao

    companion object {
        private var instance: CartDatabase? = null

        @Synchronized
        fun getInstance(context: Application): CartDatabase {
            return if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    CartDatabase::class.java,
                    "db"
                ).allowMainThreadQueries().build()
                instance as CartDatabase
            } else {
                instance as CartDatabase
            }

        }
    }
}