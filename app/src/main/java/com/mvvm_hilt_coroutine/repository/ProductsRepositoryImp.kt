package com.mvvm_hilt_coroutine.repository

import com.mvvm_hilt_coroutine.api.PhotoPickerInterface
import com.mvvm_hilt_coroutine.model.ProductResponse
import com.mvvm_hilt_coroutine.utils.BaseApiResponse
import com.mvvm_hilt_coroutine.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProductsRepositoryImp @Inject constructor(private val photoApi:PhotoPickerInterface) :ProductsRepository,
    BaseApiResponse() {
    override fun getProducts(): Flow<NetworkResult<ProductResponse>> {
        return flow {
            emit(NetworkResult.Loading())
            emit(safeApiCall { photoApi.getPhotos()})
        }
    }

}