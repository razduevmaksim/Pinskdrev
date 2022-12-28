package com.maksapp.pinskdrev.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.maksapp.pinskdrev.common.Common
import com.maksapp.pinskdrev.model.ProductModel

class DetailViewModel : ViewModel() {
    private var mutableDetailModelListData: MutableLiveData<ProductModel>? = null

    fun getMutableDetailModelLiveData(): MutableLiveData<ProductModel> {
        if (mutableDetailModelListData == null)
            mutableDetailModelListData = MutableLiveData()
        mutableDetailModelListData!!.value = Common.product_selected
        return mutableDetailModelListData!!
    }
}