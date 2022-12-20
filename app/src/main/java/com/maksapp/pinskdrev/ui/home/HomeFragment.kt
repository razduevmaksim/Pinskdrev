package com.maksapp.pinskdrev.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.Unbinder
import com.asksira.loopingviewpager.LoopingViewPager
import com.maksapp.pinskdrev.R
import com.maksapp.pinskdrev.adapter.NewBestDealsAdapter
import com.maksapp.pinskdrev.adapter.NewPopularCategoriesAdapter
import com.maksapp.pinskdrev.databinding.FragmentHomeBinding
import com.maksapp.pinskdrev.model.PopularCategoryModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment() {

    var recyclerView: RecyclerView? = null
    var viewPager: LoopingViewPager? = null

    var layoutAnimationController: LayoutAnimationController? = null

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initView(root)

        homeViewModel.popularList.observe(viewLifecycleOwner, Observer {
            val listData = it
            val adapter = NewPopularCategoriesAdapter(requireContext(), listData)
            recyclerView!!.adapter = adapter
            recyclerView!!.layoutAnimation = layoutAnimationController
        })
        homeViewModel.bestDealList.observe(viewLifecycleOwner, Observer {
            val adapter = NewBestDealsAdapter(requireContext(), it, false)
            viewPager!!.adapter = adapter
        })
        return root
    }

    private fun initView(root: View) {
        recyclerView = root.recycler_view_popular as RecyclerView
        recyclerView!!.setHasFixedSize(true)
        recyclerView!!.layoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        viewPager = root.looping_view_pager as LoopingViewPager
        layoutAnimationController = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_item_from_left)
    }

    override fun onResume() {
        super.onResume()
        viewPager!!.resumeAutoScroll()
    }

    override fun onPause() {
        super.onPause()
        viewPager!!.pauseAutoScroll()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}