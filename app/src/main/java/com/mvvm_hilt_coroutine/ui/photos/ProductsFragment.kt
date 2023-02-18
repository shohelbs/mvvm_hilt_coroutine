package com.mvvm_hilt_coroutine.ui.photos

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mvvm_hilt_coroutine.model.ProductResponse
import com.mvvm_hilt_coroutine.ui.photos.view_model.ProductsViewModel
import com.mvvm_hilt_coroutine.utils.NetworkResult
import com.photopicker.databinding.FragmentProductsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsFragment : Fragment() {

    private var _binding: FragmentProductsBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<ProductsViewModel>()

    companion object {
        const val UI_DATA_MODEL = "ui_data_model"

        @JvmStatic
        fun newInstance() =
            ProductsFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
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

       // Set Data

        viewModel.fetchPhotos()
        viewModel.response.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    response.data?.let {
                       showPhotos(it)
                    }
                }

                is NetworkResult.Error -> {
                    Log.e("data",">> Error")
                }

                is NetworkResult.Loading -> {
                    Log.e("data",">> Loader")
                }
            }
        }
    }

    private fun showPhotos(pickerResponse: ProductResponse) {
        binding.photoList.apply {
            this.layoutManager = LinearLayoutManager(requireContext())
            pickerResponse.products?.let {
                this.adapter = ProductsAdapter(it)
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}