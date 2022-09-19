package com.example.apphearthstone.data.remote

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class HearthstoneModel(
    @SerializedName("cardId")
    val cardId : String,

    @SerializedName("name")
    val name : String,

    @SerializedName("cardSet")
    val cardSet : String,

    @SerializedName("type")
    val type : String,

    @SerializedName("rarity")
    val rarity : String? = null,

    @SerializedName("cost")
    val cost : Int? = null,

    @SerializedName("attack")
    val attack : Int? = null,

    @SerializedName("health")
    val health : Int? = null,

    @SerializedName("faction")
    val faction : String? = null,

    @SerializedName("text")
    val text : String,

    @SerializedName("flavor")
    val flavor : String? = null,

    @SerializedName("artist")
    val artist : String? = null,

    @SerializedName("img")
    val img : String? = null,

    @SerializedName("playerClass")
    val playerClass : String
) : Serializable