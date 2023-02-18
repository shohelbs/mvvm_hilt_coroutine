package com.mvvm_hilt_coroutine.ui.products

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.mvvm_hilt_coroutine.model.Product
import com.mvvm_hilt_coroutine.model.ProductResponse
import com.mvvm_hilt_coroutine.ui.base.BaseFragment
import com.mvvm_hilt_coroutine.ui.products.view_model.ProductsViewModel
import com.mvvm_hilt_coroutine.utils.NetworkResult
import com.photopicker.databinding.FragmentProductsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsDetailsFragment : BaseFragment() {

    private var _binding: FragmentProductsBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<ProductsViewModel>()
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
        _binding = FragmentProductsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}