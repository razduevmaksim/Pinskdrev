package com.maksapp.pinskdrev.callback

import com.maksapp.pinskdrev.model.BestDealModel

interface IBestDealCallback {
    fun onBestDealSuccess(bestDealList: List<BestDealModel>)
    fun onBestDealLoadFailed(message: String)
}