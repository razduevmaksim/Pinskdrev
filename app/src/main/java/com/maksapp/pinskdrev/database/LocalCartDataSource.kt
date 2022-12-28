package com.maksapp.pinskdrev.database

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

class LocalCartDataSource(private val cartDao: CartDao) : CartDataSource {
    override fun getAllCart(uid: String): Flowable<List<CartItem>> {
        return cartDao.getAllCart(uid)
    }

    override fun countItemInCart(uid: String): Single<Int> {
        return cartDao.countItemInCart(uid)
    }

    override fun sumPrice(uid: String): Single<Long> {
        return cartDao.sumPrice(uid)
    }

    override fun getItemInCart(uid: String, productId: String): Single<CartItem> {
        return cartDao.getItemInCart(uid, productId)
    }

    override fun insertOrReplaceAll(vararg cartItem: CartItem): Completable {
        return cartDao.insertOrReplaceAll(*cartItem)
    }

    override fun updateCart(cart: CartItem): Single<Int> {
        return cartDao.updateCart(cart)
    }

    override fun deleteCart(cart: CartItem): Single<Int> {
        return cartDao.deleteCart(cart)
    }

    override fun cleanCart(uid: String): Single<Int> {
        return cartDao.cleanCart(uid)
    }

}