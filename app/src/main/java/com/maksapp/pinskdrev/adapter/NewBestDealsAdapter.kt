package com.maksapp.pinskdrev.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.asksira.loopingviewpager.LoopingPagerAdapter
import com.bumptech.glide.Glide
import com.maksapp.pinskdrev.R
import com.maksapp.pinskdrev.model.BestDealModel

class NewBestDealsAdapter(
    internal var context: Context,
    itemList: List<BestDealModel>,
    isInfinite: Boolean
) :
    LoopingPagerAdapter<BestDealModel>(itemList, isInfinite) {
    override fun bindView(convertView: View, listPosition: Int, viewType: Int) {
        val imageView = convertView!!.findViewById<ImageView>(R.id.image_view_best_deal)
        val textView = convertView!!.findViewById<TextView>(R.id.text_view_best_deal)

        Glide.with(context).load(itemList!![listPosition].image).into(imageView)
        textView.text = itemList!![listPosition].name
    }

    override fun inflateView(viewType: Int, container: ViewGroup, listPosition: Int): View {
        return LayoutInflater.from(context)
            .inflate(R.layout.layout_best_deals_item, container, false)
    }
}