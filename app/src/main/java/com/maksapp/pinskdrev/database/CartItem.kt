package com.maksapp.pinskdrev.database

import android.widget.ImageView
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "Cart")
class CartItem(
    @PrimaryKey(autoGenerate = true) var productId: Int = 0,

    @ColumnInfo(name = "productName") var productName: String? = null,

    @ColumnInfo(name = "productImage") var productImage: String? = null,

    @ColumnInfo(name = "productPrice") var productPrice: String? = null,

    @ColumnInfo(name = "productQuantity") var productQuantity: String? = null
)