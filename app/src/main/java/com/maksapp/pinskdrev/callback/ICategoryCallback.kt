package com.maksapp.pinskdrev.callback

import com.maksapp.pinskdrev.model.BestDealModel
import com.maksapp.pinskdrev.model.CategoryModel

interface ICategoryCallback {
    fun onCategorySuccess(categoryList: List<CategoryModel>)
    fun onCategoryLoadFailed(message: String)
}