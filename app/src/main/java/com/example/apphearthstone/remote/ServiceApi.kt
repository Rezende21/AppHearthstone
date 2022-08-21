package com.example.apphearthstone.remote

import com.example.apphearthstone.model.HearthstoneModel
import com.example.apphearthstone.utis.Constants.RAPIDAPI_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ServiceApi {

    @GET("/cards/classes/{cardClass}")
    suspend fun getCardFromClass(
        @Path("cardClass") cardClass : String,
        @Query("rapidapi-key") api_key : String = RAPIDAPI_KEY
    ) : Response<List<HearthstoneModel>>

    @GET("/cards/{singleCard}")
    suspend fun getSingleCard(
        @Path("singleCard") singleCard : String,
        @Query("rapidapi-key") api_key: String = RAPIDAPI_KEY
    ) : Response<List<HearthstoneModel>>
}