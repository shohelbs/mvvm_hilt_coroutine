package com.mvvm_hilt_coroutine.api

import com.mvvm_hilt_coroutine.model.ProductResponse
import retrofit2.Response
import retrofit2.http.GET

interface PhotoPickerInterface {

    @GET(UrlHelper.PRODUCTS)
    suspend fun getPhotos(): Response<ProductResponse>
}