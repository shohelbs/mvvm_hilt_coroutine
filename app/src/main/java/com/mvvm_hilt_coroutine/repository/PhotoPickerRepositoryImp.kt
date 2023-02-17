package com.mvvm_hilt_coroutine.repository

import com.mvvm_hilt_coroutine.api.PhotoPickerInterface
import com.mvvm_hilt_coroutine.model.BaseApiResponse
import com.mvvm_hilt_coroutine.model.DogResponse
import com.mvvm_hilt_coroutine.utils.NetworkResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PhotoPickerRepositoryImp @Inject constructor(private val photoApi:PhotoPickerInterface) :PhotoPickerRepository,
    BaseApiResponse() {
    override fun getDog(): Flow<NetworkResult<DogResponse>> {
        return flow {
            emit(NetworkResult.Loading())
            emit(safeApiCall { photoApi.getDog()})
        }
    }

}