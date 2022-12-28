package com.maksapp.pinskdrev.ui.product

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.maksapp.pinskdrev.common.Common
import com.maksapp.pinskdrev.model.ProductModel

class ProductViewModel : ViewModel() {
    private var mutableProductModelListData: MutableLiveData<List<ProductModel>>? = null

    fun getMutableProductModelLiveData(): MutableLiveData<List<ProductModel>> {
        if (mutableProductModelListData == null)
            mutableProductModelListData = MutableLiveData()
        mutableProductModelListData!!.value = Common.category_selected!!.products
        return mutableProductModelListData!!
    }
}