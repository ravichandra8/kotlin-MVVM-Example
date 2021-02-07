package com.ravi.kotlin_mvvm_with_retrofit.utils

import com.ravi.kotlin_mvvm_with_retrofit.ui.photos.model.PhotoResponse

sealed class Resource() {
    object ShowLoader : Resource()
    object HideLoader : Resource()
    class Success(val photoList: List<PhotoResponse>) : Resource()
    class Error(val errorMessasge: String) : Resource()
    object Empty:Resource()
}