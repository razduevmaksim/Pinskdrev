package com.maksapp.pinskdrev.database

import androidx.lifecycle.LiveData

interface CartRepository {
    val allProducts: LiveData<List<CartItem>>

    suspend fun insert(cartItem: CartItem, onSuccess: () -> Unit)
    suspend fun delete(cartItem: CartItem, onSuccess: () -> Unit)
    suspend fun deleteAll()
}