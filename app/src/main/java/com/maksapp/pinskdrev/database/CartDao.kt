package com.maksapp.pinskdrev.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CartDao {

    @Query("SELECT * FROM Cart")
    fun getAll(): LiveData<List<CartItem>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(cartItem: CartItem)

    @Query("DELETE FROM Cart WHERE productId =:id")
    fun deleteById(id: String)

    @Query("DELETE FROM Cart")
    suspend fun deleteAll()
}