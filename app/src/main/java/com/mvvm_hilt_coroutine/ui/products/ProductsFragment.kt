package com.mvvm_hilt_coroutine.ui.products

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mvvm_hilt_coroutine.model.Product
import com.mvvm_hilt_coroutine.model.ProductResponse
import com.mvvm_hilt_coroutine.ui.base.BaseFragment
import com.mvvm_hilt_coroutine.ui.products.view_model.ProductsViewModel
import com.mvvm_hilt_coroutine.utils.NetworkResult
import com.photopicker.databinding.FragmentProductsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductsFragment : BaseFragment() {

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
        viewModel.fetchPhotos()
        viewModel.response.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    binding.progressBar.visibility = View.GONE
                    response.data?.let {
                       showPhotos(it)
                    }
                }

                is NetworkResult.Error -> {
                    binding.progressBar.visibility = View.GONE
                }

                is NetworkResult.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun showPhotos(pickerResponse: ProductResponse) {
        binding.photoList.apply {
            this.layoutManager = LinearLayoutManager(requireContext())
            pickerResponse.products?.let {
                this.adapter = ProductsAdapter(it,object : ProductsAdapter.ProductCallback{
                    override fun onItemClick(item: Product, position: Int) {
                        myCommunicator?.addContentFragment(
                            ProductsDetailsFragment.newInstance(item),true)
                    }
                })
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}