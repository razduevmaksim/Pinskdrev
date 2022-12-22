package com.maksapp.pinskdrev.ui.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andremion.counterfab.CounterFab
import com.bumptech.glide.Glide
import com.maksapp.pinskdrev.R
import com.maksapp.pinskdrev.databinding.FragmentDetailBinding
import com.maksapp.pinskdrev.model.ProductModel
import kotlinx.android.synthetic.main.fragment_detail.view.*
import kotlinx.android.synthetic.main.fragment_product.view.*

class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private var productImageView: ImageView? = null
    private var btnCart: CounterFab? = null
    private var productName: TextView? = null
    private var productPrice: TextView? = null
    private var productDescription: TextView? = null
    private var buttonMinus: Button? = null
    private var buttonPlus: Button? = null
    private var countProducts: TextView? = null


    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val detailViewModel =
            ViewModelProvider(this).get(DetailViewModel::class.java)

        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val root: View = binding.root
        initView(root)

        detailViewModel.getMutableDetailModelLiveData().observe(viewLifecycleOwner, Observer {
            displayInfo(it)
        })

        return root
    }

    private fun displayInfo(it: ProductModel?) {
        Glide.with(requireContext()).load(it!!.image).into(productImageView!!)
        productName!!.text = StringBuilder(it.name!!)
        productPrice!!.text = StringBuilder(it.price!!.toString())
        productDescription!!.text = StringBuilder(it.description!!)
    }

    private fun initView(root: View) {
        productImageView = root.image_view_detail as ImageView
        btnCart = root.btn_cart as CounterFab
        productName = root.text_view_detail_name as TextView
        productPrice = root.text_view_detail_price as TextView
        productDescription = root.text_view_detail_description as TextView
        buttonMinus = root.button_minus as Button
        buttonPlus = root.button_plus as Button
        countProducts = root.count_details as TextView
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}