package com.maksapp.pinskdrev.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.maksapp.pinskdrev.EventBus.PopularCategoryClick
import com.maksapp.pinskdrev.R
import com.maksapp.pinskdrev.callback.IRecyclerItemClickListener
import com.maksapp.pinskdrev.common.Common
import com.maksapp.pinskdrev.model.PopularCategoryModel
import kotlinx.android.synthetic.main.layout_popular_categories_item.view.*
import org.greenrobot.eventbus.EventBus


class NewPopularCategoriesAdapter(
    internal var context: Context, private var popularCategoryModels: List<PopularCategoryModel>
) : RecyclerView.Adapter<NewPopularCategoriesAdapter.NewViewHolder>() {
    inner class NewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        var categoryName: TextView? = null

        var categoryImage: ImageView? = null
        private var listener: IRecyclerItemClickListener? = null

        fun setListener(listener: IRecyclerItemClickListener) {
            this.listener = listener
        }

        override fun onClick(p0: View?) {
            listener!!.onItemClick(p0!!, adapterPosition)
        }

        init {
            categoryName = itemView.text_view_category_name as TextView
            categoryImage = itemView.category_image as ImageView
            itemView.setOnClickListener(this)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewViewHolder {
        return NewViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.layout_popular_categories_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NewViewHolder, position: Int) {
        Glide.with(context).load(popularCategoryModels[position].image).into(holder.categoryImage!!)
        holder.categoryName!!.text = popularCategoryModels[position].name

        holder.setListener(object : IRecyclerItemClickListener {
            override fun onItemClick(view: View, pos: Int) {
                Common.popular_category_selected = popularCategoryModels[pos]
                EventBus.getDefault()
                    .postSticky(PopularCategoryClick(true, popularCategoryModels[pos]))
            }
        })
    }

    override fun getItemCount(): Int {
        return popularCategoryModels.size
    }
}