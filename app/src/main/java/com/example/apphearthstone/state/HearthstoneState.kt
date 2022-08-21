package com.example.apphearthstone.state

sealed class HearthstoneState<T> (val data : T? = null, val message : String? = null) {
    class Success<T>(data: T) : HearthstoneState<T>(data)
    class Error<T>(message : String, data : T? = null) : HearthstoneState<T>(data, message)
    class Loading<T> : HearthstoneState<T>()
}