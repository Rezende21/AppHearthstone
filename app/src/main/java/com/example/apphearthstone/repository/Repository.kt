package com.example.apphearthstone.repository

import com.example.apphearthstone.data.remote.HearthstoneModel
import retrofit2.Response

interface Repository{

    suspend fun getCardFromClass(cardClass : String) : Response<List<HearthstoneModel>>
    suspend fun serchSingleCard(singleCard: String) : Response<List<HearthstoneModel>>
}