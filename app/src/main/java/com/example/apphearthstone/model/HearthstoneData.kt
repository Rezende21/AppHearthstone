package com.example.apphearthstone.model

data class HearthstoneData(
    val cardId : String,
    val name : String,
    val cardSet : String,
    val type : String,
    val rarity : String,
    val cost : Int,
    val attack : Int,
    val health : Int,
    val faction : String,
    val text : String,
    val flavor : String,
    val artist : String,
    val img : String,
    val playerClass : String
)