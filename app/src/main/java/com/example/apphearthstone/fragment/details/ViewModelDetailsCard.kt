package com.example.apphearthstone.fragment.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apphearthstone.model.HearthstoneModel
import com.example.apphearthstone.repository.RepositoryHearthstone
import com.example.apphearthstone.state.HearthstoneState
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import java.lang.Exception

class ViewModelDetailsCard : ViewModel() {

    private val repository = RepositoryHearthstone()
    var result : MutableLiveData<HearthstoneState<List<HearthstoneModel>>> = MutableLiveData()

    fun serchSingleCard(cardId: String) {
        viewModelScope.launch {
            result.value = HearthstoneState.Loading()
            try {
                val searchCard = repository.serchSingleCard(cardId)
                result.value = setResultState(searchCard)
            } catch (e : Exception) {
                when(e) {
                    is IOException -> result.value = HearthstoneState.Error("Nao foi possivel fazer a conecxão")
                    else -> result.value = HearthstoneState.Error("Nao foi possivel fazer a conecxão")
                }
            }
        }
    }

    private fun setResultState(searchCard: Response<List<HearthstoneModel>>): HearthstoneState<List<HearthstoneModel>> {
        if (searchCard.isSuccessful) {
            searchCard.body()?.let {
                return HearthstoneState.Success(it)
            }
        }
        return HearthstoneState.Error(searchCard.message())
    }
}