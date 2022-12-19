package com.maksapp.pinskdrev.callback

import com.maksapp.pinskdrev.model.PopularCategoryModel

interface IPopularLoadCallback {
    fun onPopularSuccess(popularModelList: List<PopularCategoryModel>)
    fun onPopularLoadFailed(message: String)
}