package com.ravi.kotlin_mvvm_with_retrofit.repository

import com.ravi.kotlin_mvvm_with_retrofit.RetrofitInstance
import com.ravi.kotlin_mvvm_with_retrofit.ui.photos.model.PhotoResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response

class MediaRepository {
    init{
        println("threadname: snsRepository ${Thread.currentThread().name}")
    }

     val getPhotoApi: Flow<MutableList<PhotoResponse>> = flow {
         while(true){
            val latestPhotos= RetrofitInstance.api.getPhotos()
                emit(latestPhotos)
             delay(5000)
         }

     }
}