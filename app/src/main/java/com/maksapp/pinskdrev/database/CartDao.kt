package com.maksapp.pinskdrev.database

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface CartDao {
    @Query("SELECT * FROM Cart WHERE uid=:uid")
    fun getAllCart(uid: String): Flowable<List<CartItem>>

    @Query("SELECT COUNT(*) FROM Cart WHERE uid=:uid")
    fun countItemInCart(uid: String): Single<Int>

    @Query("SELECT SUM(productPrice*productQuantity) FROM Cart WHERE uid=:uid")
    fun sumPrice(uid: String): Single<Long>

    @Query("SELECT * FROM Cart WHERE uid=:uid AND productId=:productId")
    fun getItemInCart(uid: String, productId: String): Single<CartItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertOrReplaceAll(vararg cartItem: CartItem): Completable

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateCart(cart: CartItem): Single<Int>

    @Delete
    fun deleteCart(cart: CartItem): Single<Int>

    @Query("DELETE FROM Cart WHERE uid=:uid")
    fun cleanCart(uid: String): Single<Int>
}