package com.ravi.kotlin_mvvm_with_retrofit.ui.photos.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ravi.kotlin_mvvm_with_retrofit.repository.MediaRepository
import com.ravi.kotlin_mvvm_with_retrofit.ui.photos.model.PhotoResponse
import com.ravi.kotlin_mvvm_with_retrofit.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.Response

class PhotoViewModel(val mediaRepository: MediaRepository) : ViewModel() {

    var photosMutableLiveData: MutableStateFlow<Resource> = MutableStateFlow(Resource.Empty)


    open fun getPhotoInformation() {
        viewModelScope.launch {
            photosMutableLiveData.value = Resource.ShowLoader
            mediaRepository.getPhotoApi
                    .catch { exception ->
                        Log.d("execpetion", exception.message ?: "ravi")
                    }.collect {
                        handlePhotoInformation(it)
                    }

        }
    }

    private fun handlePhotoInformation(response: MutableList<PhotoResponse>) {
        val job: Job = viewModelScope.launch(Dispatchers.Main) {
            photosMutableLiveData.value = Resource.HideLoader
        }
        viewModelScope.launch {
            job.join()
            photosMutableLiveData.value = Resource.Success(response)
        }

    }
}