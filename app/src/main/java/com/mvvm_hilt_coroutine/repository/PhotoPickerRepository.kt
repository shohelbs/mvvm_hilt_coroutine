package com.mvvm_hilt_coroutine.repository

import com.mvvm_hilt_coroutine.model.DogResponse
import com.mvvm_hilt_coroutine.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface PhotoPickerRepository {
    fun getDog(): Flow<NetworkResult<DogResponse>>
}