package com.maksapp.pinskdrev.database

import androidx.lifecycle.LiveData
import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface CartDao {

    @Query("SELECT * FROM Cart")
    fun getAll(): LiveData<List<CartItem>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(cartItem: CartItem)

    @Query("DELETE FROM Cart WHERE productId =:id")
    fun deleteById(id:Int)

    @Query("DELETE FROM Cart")
    suspend fun deleteAll()
    }