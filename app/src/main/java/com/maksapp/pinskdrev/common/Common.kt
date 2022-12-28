package com.maksapp.pinskdrev.common

import com.maksapp.pinskdrev.model.CategoryModel
import com.maksapp.pinskdrev.model.ProductModel

object Common {
    var product_selected: ProductModel? = null
    var category_selected: CategoryModel? = null
    const val CATEGORY_REF: String = "Category"
    const val FULL_WIDTH_COLUMN: Int = 1
    const val DEFAULT_COMMON_COUNT: Int = 0
    const val BEST_DEALS_REF = "BestDeals"
    const val POPULAR_REF = "MostPopular"
}