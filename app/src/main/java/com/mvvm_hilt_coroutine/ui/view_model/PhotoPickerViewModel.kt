package com.mvvm_hilt_coroutine.ui.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mvvm_hilt_coroutine.model.DogResponse
import com.mvvm_hilt_coroutine.repository.PhotoPickerRepository
import com.mvvm_hilt_coroutine.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotoPickerViewModel @Inject constructor(private val repository: PhotoPickerRepository) :
    ViewModel() {

    private val _response: MutableLiveData<NetworkResult<DogResponse>> = MutableLiveData()
    val response: LiveData<NetworkResult<DogResponse>> = _response

    fun fetchDogResponse() = viewModelScope.launch {
        repository.getDog().collect { values ->
            _response.value = values
        }
    }

}