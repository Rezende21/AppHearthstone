package com.example.apphearthstone.remote

import com.example.apphearthstone.utis.Constants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Modulo {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api : ServiceApi by lazy {
        retrofit.create(ServiceApi::class.java)
    }
}