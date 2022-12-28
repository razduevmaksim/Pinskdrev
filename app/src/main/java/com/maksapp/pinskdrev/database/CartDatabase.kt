package com.maksapp.pinskdrev.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(version = 1, entities = [CartItem::class], exportSchema = false)
abstract class CartDatabase : RoomDatabase() {
    abstract fun cartDao(): CartDao

    companion object {
        private var instance: CartDatabase? = null

        fun getInstance(context: Context): CartDatabase {
            if (instance == null) instance = Room.databaseBuilder<CartDatabase>(
                context, CartDatabase::class.java!!, "PinskdrevDB1"
            ).build()
            return instance!!
        }
    }
}