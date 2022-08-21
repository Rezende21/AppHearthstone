package com.example.apphearthstone.repository

import com.example.apphearthstone.model.HearthstoneModel
import com.example.apphearthstone.remote.Modulo
import retrofit2.Response

class RepositoryHearthstone {

    suspend fun getCardFromClass(cardClass : String) : Response<List<HearthstoneModel>> {
        return Modulo.api.getCardFromClass(cardClass)
    }

    suspend fun serchSingleCard(singleCard: String) : Response<List<HearthstoneModel>> {
        return Modulo.api.getSingleCard(singleCard)
        //TODO verificar se o item esta null aqui ou em outro model que posso esta fazendo
    }
}