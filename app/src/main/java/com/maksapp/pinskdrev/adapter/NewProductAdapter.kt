package com.maksapp.pinskdrev.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.maksapp.pinskdrev.EventBus.CategoryClick
import com.maksapp.pinskdrev.EventBus.ProductClick
import com.maksapp.pinskdrev.R
import com.maksapp.pinskdrev.callback.IRecyclerItemClickListener
import com.maksapp.pinskdrev.common.Common
import com.maksapp.pinskdrev.model.ProductModel
import kotlinx.android.synthetic.main.layout_product_item.view.*
import org.greenrobot.eventbus.EventBus

class NewProductAdapter(
    internal var context: Context, private var productList: List<ProductModel>
) : RecyclerView.Adapter<NewProductAdapter.NewViewHolder>() {
    inner class NewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        var productName: TextView? = null
        var productPrice: TextView? = null
        var productImage: ImageView? = null
        private var productImageFavorite: ImageView? = null
        private var productImageShoppingCart: ImageView? = null
        private var listener: IRecyclerItemClickListener? = null

        init {
            productName = itemView.text_view_product_name as TextView
            productPrice = itemView.text_view_product_price as TextView
            productImage = itemView.image_view_product as ImageView
            productImageFavorite = itemView.image_view_favorite as ImageView
            productImageShoppingCart = itemView.image_view_shopping_cart as ImageView
            itemView.setOnClickListener(this)

        }
        fun setListener(listener: IRecyclerItemClickListener) {
            this.listener = listener
        }

        override fun onClick(p0: View?) {
            listener!!.onItemClick(p0!!, adapterPosition)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): NewProductAdapter.NewViewHolder {
        return NewViewHolder(
            LayoutInflater.from(context).inflate(R.layout.layout_product_item, parent, false)
        )
    }

    override fun onBindViewHolder(
        holder: NewProductAdapter.NewViewHolder, position: Int
    ) {
        Glide.with(context).load(productList[position].image).into(holder.productImage!!)
        holder.productName!!.text = productList[position].name
        holder.productPrice!!.text = productList[position].price.toString()

        //Event
        holder.setListener(object : IRecyclerItemClickListener {
            override fun onItemClick(view: View, pos: Int) {
                Common.food_selected = productList[pos]
                EventBus.getDefault().postSticky(ProductClick(true, productList[pos]))
            }

        })
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}