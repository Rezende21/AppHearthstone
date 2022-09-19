package com.example.apphearthstone.repository

import com.example.apphearthstone.data.remote.HearthstoneModel
import com.example.apphearthstone.data.remote.ServiceApi
import retrofit2.Response
import javax.inject.Inject

class RepositoryHearthstone @Inject constructor(
    private val api : ServiceApi) : Repository {

    override suspend fun getCardFromClass(cardClass : String) : Response<List<HearthstoneModel>> {
        return api.getCardFromClass(cardClass)
    }

    override suspend fun serchSingleCard(singleCard: String) : Response<List<HearthstoneModel>> {
        return api.getSingleCard(singleCard)

    }
}