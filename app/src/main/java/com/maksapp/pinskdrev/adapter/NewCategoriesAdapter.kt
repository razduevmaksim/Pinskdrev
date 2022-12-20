package com.maksapp.pinskdrev.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.maksapp.pinskdrev.R
import com.maksapp.pinskdrev.common.Common
import com.maksapp.pinskdrev.model.CategoryModel
import com.maksapp.pinskdrev.model.PopularCategoryModel
import kotlinx.android.synthetic.main.layout_category_item.view.*
import kotlinx.android.synthetic.main.layout_popular_categories_item.view.*

class NewCategoriesAdapter(
    internal var context: Context,
    internal var categoriesList: List<CategoryModel>
) : RecyclerView.Adapter<NewCategoriesAdapter.NewViewHolder>() {
    inner class NewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var categoryName: TextView? = null

        var categoryImage: ImageView? = null

        init {
            categoryName = itemView.text_view_category as TextView
            categoryImage = itemView.image_view_category as ImageView
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NewCategoriesAdapter.NewViewHolder {
        return NewViewHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.layout_category_item, parent, false)
        )
    }

    override fun onBindViewHolder(
        holder: NewCategoriesAdapter.NewViewHolder,
        position: Int
    ) {
        Glide.with(context).load(categoriesList.get(position).image)
            .into(holder.categoryImage!!)
        holder.categoryName!!.text = categoriesList.get(position).name
    }

    override fun getItemCount(): Int {
        return categoriesList.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (categoriesList.size == 1) {
            Common.DEFAULT_COMMON_COUNT
        } else {
            if (categoriesList.size % 2 == 0) {
                Common.DEFAULT_COMMON_COUNT
            } else {
                if (position > 1 && position == categoriesList.size - 1) {
                    Common.FULL_WIDTH_COLUMN
                } else {
                    Common.DEFAULT_COMMON_COUNT
                }
            }
        }
    }
}