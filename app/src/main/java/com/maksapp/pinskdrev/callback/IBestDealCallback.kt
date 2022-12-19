package com.maksapp.pinskdrev.callback

import com.maksapp.pinskdrev.model.BestDealModel
import com.maksapp.pinskdrev.model.PopularCategoryModel

interface IBestDealCallback {
    fun onBestDealSuccess(bestDealList: List<BestDealModel>)
    fun onBestDealLoadFailed(message: String)
}