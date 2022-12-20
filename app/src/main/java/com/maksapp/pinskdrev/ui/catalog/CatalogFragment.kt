package com.maksapp.pinskdrev.ui.catalog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.maksapp.pinskdrev.R
import com.maksapp.pinskdrev.adapter.NewCategoriesAdapter
import com.maksapp.pinskdrev.common.Common
import com.maksapp.pinskdrev.common.SpacesItemDecorations
import com.maksapp.pinskdrev.databinding.FragmentCatalogBinding
import kotlinx.android.synthetic.main.fragment_catalog.view.*

class CatalogFragment : Fragment() {

    private var _binding: FragmentCatalogBinding? = null
    var recyclerView: RecyclerView? = null
    private lateinit var layoutAnimationController: LayoutAnimationController
    private var adapter: NewCategoriesAdapter? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val catalogViewModel = ViewModelProvider(this)[CatalogViewModel::class.java]

        _binding = FragmentCatalogBinding.inflate(inflater, container, false)
        val root: View = binding.root

        initViews(root)

        catalogViewModel.getCategoryList().observe(viewLifecycleOwner) {
            adapter = NewCategoriesAdapter(requireContext(), it)
            recyclerView!!.adapter = adapter
            recyclerView!!.layoutAnimation = layoutAnimationController
        }
        return root
    }

    private fun initViews(root: View) {
        layoutAnimationController =
            AnimationUtils.loadLayoutAnimation(context, R.anim.layout_item_from_left)
        recyclerView = root.recycler_view_catalog as RecyclerView
        recyclerView!!.setHasFixedSize(true)
        val layoutManager = GridLayoutManager(context, 2)
        layoutManager.orientation = RecyclerView.VERTICAL
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (adapter != null) {
                    when (adapter!!.getItemViewType(position)) {
                        Common.DEFAULT_COMMON_COUNT -> 1
                        Common.FULL_WIDTH_COLUMN -> 2
                        else -> 1
                    }
                } else {
                    -1
                }
            }
        }
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.addItemDecoration(SpacesItemDecorations(8))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}