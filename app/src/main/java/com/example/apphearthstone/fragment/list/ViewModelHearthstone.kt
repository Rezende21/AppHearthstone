package com.example.apphearthstone.fragment.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apphearthstone.model.HearthstoneModel
import com.example.apphearthstone.repository.RepositoryHearthstone
import com.example.apphearthstone.state.HearthstoneState
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class ViewModelHearthstone : ViewModel() {

    private val repository = RepositoryHearthstone()
    val results : MutableLiveData<HearthstoneState<List<HearthstoneModel>>> = MutableLiveData()

    fun getCardFromClass(cardClass : String) {
        viewModelScope.launch {
            results.value = HearthstoneState.Loading()
            try {
                val response = repository.getCardFromClass(cardClass)
                results.value = setResultState(response)
            } catch (e : Exception) {
                when(e) {
                    is IOException -> results.value = HearthstoneState.Error("Nao foi possivel fazer a conecxão")
                    else -> results.value = HearthstoneState.Error("Nao foi possivel fazer a conecxão")
                }
            }
        }
    }

    private fun setResultState(searchList: Response<List<HearthstoneModel>>): HearthstoneState<List<HearthstoneModel>>? {
        if (searchList.isSuccessful) {
            searchList.body()?.let {
                return HearthstoneState.Success(it)
            }
        }
        return HearthstoneState.Error(searchList.message())
    }
}