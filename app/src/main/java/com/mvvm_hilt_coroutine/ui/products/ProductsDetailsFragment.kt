package com.mvvm_hilt_coroutine.ui.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.google.gson.Gson
import com.mvvm_hilt_coroutine.model.Product
import com.mvvm_hilt_coroutine.ui.base.BaseFragment
import com.photopicker.databinding.FragmentProductsDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsDetailsFragment : BaseFragment() {

    private var _binding: FragmentProductsDetailsBinding? = null
    private val binding get() = _binding!!

    private var product:Product?=null

    companion object {
        const val UI_DATA_MODEL = "ui_data_model"

        @JvmStatic
        fun newInstance(product: Product) =
            ProductsDetailsFragment().apply {
                arguments = Bundle().apply {
                    val gson = Gson()
                    putString(UI_DATA_MODEL, gson.toJson(product))
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val gson = Gson()
            val uiModelListString = it.getString(UI_DATA_MODEL, "")
            if (uiModelListString.isNotEmpty()) {
                product = gson.fromJson(uiModelListString, Product::class.java)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductsDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setProductDetails()
    }

    private fun setProductDetails() {
        product?.images?.let {
            binding.productPager.adapter = ProductDetailsAdapter(it)
            TabLayoutMediator(binding.tabLayout,binding.productPager) { _, _-> }.attach()
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}