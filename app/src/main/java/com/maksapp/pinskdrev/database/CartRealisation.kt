package com.maksapp.pinskdrev.database

import androidx.lifecycle.LiveData

class CartRealisation(private val cartDao: CartDao) : CartRepository {

    override val allProducts: LiveData<List<CartItem>>
        get() = cartDao.getAll()

    override suspend fun insert(cartItem: CartItem, onSuccess: () -> Unit) {
        cartDao.insert(cartItem)
        onSuccess()
    }

    override suspend fun delete(cartItem: CartItem, onSuccess: () -> Unit) {

    }

    override suspend fun deleteAll() {
        cartDao.deleteAll()
    }
}
