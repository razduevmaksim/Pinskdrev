package com.maksapp.pinskdrev.ui.viewPager

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.button.MaterialButton
import com.maksapp.pinskdrev.MainActivity
import com.maksapp.pinskdrev.R
import kotlinx.android.synthetic.main.activity_view_pager.*


class ViewPagerActivity : AppCompatActivity() {
    private lateinit var onboardingAdapter: OnboardingAdapter
    private lateinit var layoutOnboardingIndicator: LinearLayout
    private lateinit var buttonOnboardingAction: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)

        layoutOnboardingIndicator = layout_onboarding_indicators
        buttonOnboardingAction = button_onboarding_action

        setOnboardingItems()

        on_boarding_view_pager.adapter = onboardingAdapter

        setOnboardingIndicator()
        setCurrentOnboardingIndicator(0)

        on_boarding_view_pager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentOnboardingIndicator(position)
            }

        })

        buttonOnboardingAction.setOnClickListener {
            if (on_boarding_view_pager.currentItem + 1 < onboardingAdapter.itemCount) {
                on_boarding_view_pager.currentItem = on_boarding_view_pager.currentItem + 1
            } else {
                startActivity(Intent(applicationContext, MainActivity::class.java))
                finish()
            }
        }
        supportActionBar?.hide()
    }

    private fun setOnboardingItems() {
        val onboardingItems: MutableList<OnboardingItem> =
            ArrayList<OnboardingItem>().toMutableList()

        val item1 = OnboardingItem()
        item1.title = "Title Item 1"
        item1.description = "Description Item 1"
        item1.image = R.drawable.view_pager_1

        val item2 = OnboardingItem()
        item2.title = "Title Item 2"
        item2.description = "Description Item 2"
        item2.image = R.drawable.view_pager_2

        val item3 = OnboardingItem()
        item3.title = "Title Item 3"
        item3.description = "Description Item 3"
        item3.image = R.drawable.view_pager_3

        onboardingItems.add(item1)
        onboardingItems.add(item2)
        onboardingItems.add(item3)

        onboardingAdapter = OnboardingAdapter(onboardingItems)
    }

    private fun setOnboardingIndicator() {
        val indicators = arrayOfNulls<ImageView>(onboardingAdapter.itemCount)
        val layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )
        layoutParams.setMargins(8, 0, 8, 0)
        for (i in indicators.indices) {
            indicators[i] = ImageView(applicationContext)
            indicators[i]!!.setImageDrawable(
                ContextCompat.getDrawable(
                    applicationContext, R.drawable.onboarding_indicator_inactive
                )
            )
            indicators[i]!!.layoutParams = layoutParams
            layoutOnboardingIndicator.addView(indicators[i])
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setCurrentOnboardingIndicator(index: Int) {
        val childCount = layout_onboarding_indicators.childCount
        var i = 0
        while (i < childCount) {
            val imageView = layout_onboarding_indicators.getChildAt(i) as ImageView
            if (i == index) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext, R.drawable.onboarding_indicator_active
                    )
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext, R.drawable.onboarding_indicator_inactive
                    )
                )
            }
            i++
        }
        if (index == onboardingAdapter.itemCount - 1) {
            buttonOnboardingAction.text = "Start"
        } else {
            buttonOnboardingAction.text = "Next"
        }
    }

}