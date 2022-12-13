package com.maksapp.pinskdrev.ui.viewPager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.maksapp.pinskdrev.R
import kotlinx.android.synthetic.main.item_container_onboarding.view.*


class OnboardingAdapter(private var onboardingItems: List<OnboardingItem>) :
    RecyclerView.Adapter<OnboardingAdapter.OnboardingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingViewHolder {
        return OnboardingViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_container_onboarding, parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: OnboardingViewHolder, position: Int) {
        holder.setOnboardingData(onboardingItems[position])
    }

    override fun getItemCount(): Int {
        return onboardingItems.size
    }

    class OnboardingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var textTitle: TextView
        private var textDescription: TextView
        private var imageOnboarding: ImageView

        init {
            textTitle = itemView.text_title
            textDescription = itemView.text_description
            imageOnboarding = itemView.image_view
        }

        fun setOnboardingData(onboardingItem: OnboardingItem) {
            textTitle.text = onboardingItem.title
            textDescription.text = onboardingItem.description
            imageOnboarding.setImageResource(onboardingItem.image)
        }
    }
}