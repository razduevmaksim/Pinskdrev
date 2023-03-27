package com.maksapp.pinskdrev.ui.product

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.maksapp.pinskdrev.common.Common
import com.maksapp.pinskdrev.model.ProductModel

class ProductViewModel : ViewModel() {
    private var mutableProductModelListData: MutableLiveData<List<ProductModel>>? = null
    private var mutableProductModelListData2: MutableLiveData<List<ProductModel>>? = null

    fun getMutableProductModelLiveData(): MutableLiveData<List<ProductModel>> {
        if (Common.popular_category_selected != null) {
            mutableProductModelListData = null
            mutableProductModelListData = MutableLiveData()
            mutableProductModelListData!!.value = Common.popular_category_selected!!.products
        }
        if (Common.category_selected != null) {
            mutableProductModelListData = null
            mutableProductModelListData = MutableLiveData()
            mutableProductModelListData!!.value = Common.category_selected!!.products
        }

        Common.category_selected = null
        Common.popular_category_selected = null

        return mutableProductModelListData!!
    }
}