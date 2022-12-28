package com.maksapp.pinskdrev.model

class BestDealModel {
    private var productId: String? = null
    private var catalogId: String? = null
    var name: String? = null
    var image: String? = null

    constructor()
    constructor(productId: String?, menuId: String?, name: String?, image: String?) {
        this.productId = productId
        this.catalogId = catalogId
        this.name = name
        this.image = image
    }
}