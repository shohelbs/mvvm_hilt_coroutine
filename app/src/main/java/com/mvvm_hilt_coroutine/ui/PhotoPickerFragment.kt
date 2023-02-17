package com.mvvm_hilt_coroutine.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mvvm_hilt_coroutine.databinding.FragmentPhotoPickerBinding
import com.mvvm_hilt_coroutine.ui.view_model.PhotoPickerViewModel
import com.mvvm_hilt_coroutine.utils.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotoPickerFragment : Fragment() {

    private var _binding: FragmentPhotoPickerBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<PhotoPickerViewModel>()

    companion object {
        const val UI_DATA_MODEL = "ui_data_model"

        @JvmStatic
        fun newInstance() =
            PhotoPickerFragment().apply {
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
        _binding = FragmentPhotoPickerBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       // Set Data

        viewModel.fetchDogResponse()
        viewModel.response.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    response.data?.let {
                       Log.e("data",">> ${response.data.message}")
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


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}