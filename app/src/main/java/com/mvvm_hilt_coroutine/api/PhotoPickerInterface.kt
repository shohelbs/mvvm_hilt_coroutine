package com.mvvm_hilt_coroutine.api

import com.mvvm_hilt_coroutine.model.DogResponse
import retrofit2.Response
import retrofit2.http.GET

interface PhotoPickerInterface {

    @GET(UrlHelper.RANDOM_URL)
    suspend fun getDog(): Response<DogResponse>
}