package com.maksapp.pinskdrev.model

class PopularCategoryModel {
    private var foodId: String? = null
    private var menuId: String? = null
    var name: String? = null
    var image: String? = null

    constructor()
    constructor(foodId: String?, menuId: String?, name: String?, image: String?) {
        this.foodId = foodId
        this.menuId = menuId
        this.name = name
        this.image = image
    }
}