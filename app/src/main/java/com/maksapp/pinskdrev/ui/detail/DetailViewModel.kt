package com.maksapp.pinskdrev.ui.detail

import android.app.Application
import androidx.lifecycle.*
import com.maksapp.pinskdrev.common.Common
import com.maksapp.pinskdrev.database.CartDatabase
import com.maksapp.pinskdrev.database.CartItem
import com.maksapp.pinskdrev.database.CartRealisation
import com.maksapp.pinskdrev.database.CartRepository
import com.maksapp.pinskdrev.model.ProductModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailViewModel(application: Application) : AndroidViewModel(application) {
    private var mutableDetailModelListData: MutableLiveData<ProductModel>? = null

    fun getMutableDetailModelLiveData(): MutableLiveData<ProductModel> {
        if (mutableDetailModelListData == null) mutableDetailModelListData = MutableLiveData()
        mutableDetailModelListData!!.value = Common.product_selected
        return mutableDetailModelListData!!
    }

    private val context = application
    private lateinit var repository: CartRepository

    //получение всех данных из room
    fun getAll(): LiveData<List<CartItem>> {
        return repository.allProducts
    }

    //инициализация БД
    fun initDatabase() {
        val daoProducts = CartDatabase.getInstance(context).cartDao()
        repository = CartRealisation(daoProducts)
    }

    //добавление данных в room
    fun insert(cartModel: CartItem, onSuccess: () -> Unit) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(cartModel) {
            onSuccess()
        }
    }
}