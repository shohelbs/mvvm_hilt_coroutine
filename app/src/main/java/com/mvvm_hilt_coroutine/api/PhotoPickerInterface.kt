package com.mvvm_hilt_coroutine.api

import com.mvvm_hilt_coroutine.model.PhotoPickerResponse
import retrofit2.Response
import retrofit2.http.GET

interface PhotoPickerInterface {

    @GET(UrlHelper.PHOTOS)
    suspend fun getPhotos(): Response<PhotoPickerResponse>
}