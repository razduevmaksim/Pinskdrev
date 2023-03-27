package com.maksapp.pinskdrev.ui.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.maksapp.pinskdrev.R
import com.maksapp.pinskdrev.adapter.NewProductAdapter
import com.maksapp.pinskdrev.databinding.FragmentProductBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_product.view.*

class ProductFragment : Fragment() {

    private var _binding: FragmentProductBinding? = null
    var recyclerView: RecyclerView? = null
    private var layoutAnimationController: LayoutAnimationController? = null
    private var adapter: NewProductAdapter? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val productViewModel = ViewModelProvider(this)[ProductViewModel::class.java]

        _binding = FragmentProductBinding.inflate(inflater, container, false)
        val root: View = binding.root
        initView(root)

        requireActivity().nav_view.visibility = GONE

        productViewModel.getMutableProductModelLiveData().observe(viewLifecycleOwner) {
            adapter = NewProductAdapter(requireContext(), it)
            recyclerView!!.adapter = adapter
            recyclerView!!.layoutAnimation = layoutAnimationController
        }

        return root
    }

    private fun initView(root: View) {
        recyclerView = root.recycler_view_product as RecyclerView
        recyclerView!!.setHasFixedSize(true)
        recyclerView!!.layoutManager = LinearLayoutManager(context)
        layoutAnimationController =
            AnimationUtils.loadLayoutAnimation(context, R.anim.layout_item_from_left)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}