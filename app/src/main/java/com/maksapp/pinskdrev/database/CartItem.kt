package com.maksapp.pinskdrev.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "Cart")
class CartItem {
    @PrimaryKey
    @NotNull
    @ColumnInfo(name = "productId")
    var productId: String = ""

    @ColumnInfo(name = "productName")
    var productName: String? = null

    @ColumnInfo(name = "productPrice")
    var productPrice: Double = 0.0

    @ColumnInfo(name = "productQuantity")
    var productQuantity: Int = 0

    @ColumnInfo(name = "uid")
    var uid: String? = ""
}