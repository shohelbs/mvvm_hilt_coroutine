package com.mvvm_hilt_coroutine.model


import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PhotoPickerResponseItem(
    @SerializedName("albumId")
    val albumId: Int?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("thumbnailUrl")
    val thumbnailUrl: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("url")
    val url: String?
) : Parcelable