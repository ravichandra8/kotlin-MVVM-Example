package com.ravi.kotlin_mvvm_with_retrofit.ui.photos.model

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

data class PhotoResponse (
    @SerializedName("albumId")
    @Expose
    val albumId:String,
    @SerializedName("id")
    @Expose
    val id:String,
    @SerializedName("title")
    @Expose
    val title:String,
    @SerializedName("url")
    @Expose
    val url:String,
    @SerializedName("thumbnailUrl")
    @Expose
    val thumbnailUrl:String, var isChecked :Boolean = false)