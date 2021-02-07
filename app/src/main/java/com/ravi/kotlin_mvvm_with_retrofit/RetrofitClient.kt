package com.ravi.kotlin_mvvm_with_retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitInstance{
    companion object {
        init {
            println("thread name: Companion ${Thread.currentThread().name}")
        }
        private val retrofit by lazy {
            val logging = HttpLoggingInterceptor().apply {
                level=HttpLoggingInterceptor.Level.BODY
            }
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
            Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }

        val api by lazy {
            retrofit.create(MediaService::class.java)
        }


    }


}



