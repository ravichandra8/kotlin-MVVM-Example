package com.ravi.kotlin_mvvm_with_retrofit.ui.photos.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ravi.kotlin_mvvm_with_retrofit.repository.MediaRepository
import com.ravi.kotlin_mvvm_with_retrofit.ui.photos.model.PhotoResponse
import com.ravi.kotlin_mvvm_with_retrofit.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

class PhotoViewModel(val mediaRepository: MediaRepository) : ViewModel() {

    var photosMutableLiveData: MutableStateFlow<Resource> = MutableStateFlow(Resource.Empty)

    open fun getPhotoInformation(){
        viewModelScope.launch(Dispatchers.IO) {
            photosMutableLiveData.value = Resource.ShowLoader
            val response:Response<List<PhotoResponse>>  =mediaRepository.getPhotoApi()
            handlePhotoInformation(response)
        }
    }

    private fun handlePhotoInformation(response: Response<List<PhotoResponse>>){
        val job: Job = viewModelScope.launch(Dispatchers.Main) {
            photosMutableLiveData.value = Resource.HideLoader
        }
        viewModelScope.launch {
            job.join()
            if(response.isSuccessful){
                response.body()?.let {
                    val photoList = response.body()
                    photosMutableLiveData.value = Resource.Success(photoList!!)
                }


            }  else{
                photosMutableLiveData.value = Resource.Error(response.message())
            }
        }
    }
}