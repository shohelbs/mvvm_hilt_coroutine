package com.mvvm_hilt_coroutine.ui.products.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvvm_hilt_coroutine.model.ProductResponse
import com.mvvm_hilt_coroutine.repository.ProductsRepository
import com.mvvm_hilt_coroutine.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductsDetailsViewModel @Inject constructor(private val repository: ProductsRepository) :
    ViewModel() {

    private val _response: MutableLiveData<NetworkResult<ProductResponse>> = MutableLiveData()
    val response: LiveData<NetworkResult<ProductResponse>> = _response

    fun fetchPhotos() = viewModelScope.launch {
        repository.getProducts().collect { values ->
            _response.value = values
        }
    }

}