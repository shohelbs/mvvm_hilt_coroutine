package com.mvvm_hilt_coroutine.repository

import com.mvvm_hilt_coroutine.model.PhotoPickerResponse
import com.mvvm_hilt_coroutine.utils.NetworkResult
import kotlinx.coroutines.flow.Flow

interface PhotoPickerRepository {
    fun getPhotos(): Flow<NetworkResult<PhotoPickerResponse>>
}