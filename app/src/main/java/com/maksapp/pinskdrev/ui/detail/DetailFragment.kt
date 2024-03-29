package com.maksapp.pinskdrev.ui.detail

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.andremion.counterfab.CounterFab
import com.bumptech.glide.Glide
import com.maksapp.pinskdrev.common.Common
import com.maksapp.pinskdrev.database.CartItem
import com.maksapp.pinskdrev.databinding.FragmentDetailBinding
import com.maksapp.pinskdrev.model.ProductModel
import kotlinx.android.synthetic.main.fragment_detail.view.*

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
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val detailViewModel = ViewModelProvider(this)[DetailViewModel::class.java]


        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val root: View = binding.root
        initView(root)
        changeNumber()
        addInformationToDatabase()
        detailViewModel.getMutableDetailModelLiveData().observe(viewLifecycleOwner) {
            displayInfo(it)
        }

        return root
    }

    private fun displayInfo(it: ProductModel?) {
        Glide.with(requireContext()).load(it!!.image).into(productImageView!!)
        productName!!.text = StringBuilder(it.name!!)
        productPrice!!.text = StringBuilder(it.price.toString())
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

    @SuppressLint("SetTextI18n")
    private fun changeNumber() {
        buttonMinus!!.setOnClickListener {
            if (countProducts!!.text.toString().toInt() <= 1) {
                countProducts!!.text = (1).toString()
            } else {
                countProducts!!.text = (countProducts!!.text.toString().toInt() - 1).toString()
            }
        }
        buttonPlus!!.setOnClickListener {
            if (countProducts!!.text.toString().toInt() >= 100) {
                countProducts!!.text = (100).toString()
            } else {
                countProducts!!.text = (countProducts!!.text.toString().toInt() + 1).toString()
            }
        }
    }

    //добавление данных в room
    private fun addInformationToDatabase() {
        val viewModel = ViewModelProvider(this)[DetailViewModel::class.java]
        var validationInValidation = true

        //события при нажатии на кнопку добавления
        btnCart!!.setOnClickListener {
            validationInValidation = true
            //инициализация БД
            viewModel.initDatabase()
            //получение всех данных из room
            viewModel.getAll().observe(viewLifecycleOwner) { listProducts ->
                if (validationInValidation) {
                    var validationAccuracy = true
                    listProducts.forEach { itemList ->
                        val productNameInDatabase = itemList.productName.toString()


                        if (productNameInDatabase == productName!!.text) {
                            validationAccuracy = false
                            Toast.makeText(
                                activity, "Товар уже был добавлен в корзину", Toast.LENGTH_SHORT
                            ).show()
                            validationInValidation = false
                            return@observe
                        }
                    }
                    if (validationAccuracy) {
                        //добавление данных в room
                        val productIdInDatabase = Common.product_selected!!.id.toString()
                        val productNameInDatabase = Common.product_selected!!.name.toString()
                        val productPriceInDatabase = Common.product_selected!!.price.toString()
                        val productImageInDatabase = Common.product_selected!!.image
                        val productQuantityInDatabase = countProducts!!.text.toString()
                        insertToDatabase(
                            productIdInDatabase,
                            productNameInDatabase,
                            productImageInDatabase,
                            productPriceInDatabase,
                            productQuantityInDatabase
                        )
                        validationInValidation = false

                        return@observe
                    }
                }
            }
        }
    }

    //добавление данных в room
    private fun insertToDatabase(
        productId: String,
        productName: String,
        productImage: String?,
        productPrice: String,
        productQuantity: String
    ) {
        val viewModel = ViewModelProvider(this)[DetailViewModel::class.java]

        //инициализация БД
        viewModel.initDatabase()

        viewModel.insert(
            CartItem(
                productIdDefault = productId,
                productName = productName,
                productImage = productImage,
                productPrice = productPrice,
                productQuantity = productQuantity
            )
        ) {}
        Toast.makeText(activity, "Товар добавлен в корзину", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}