package com.mvvm_hilt_coroutine.repository

import com.mvvm_hilt_coroutine.model.ProductResponse
import com.mvvm_hilt_coroutine.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {
    fun getProducts(): Flow<NetworkResult<ProductResponse>>
}