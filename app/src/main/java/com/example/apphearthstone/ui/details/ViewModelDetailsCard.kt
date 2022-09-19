package com.example.apphearthstone.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apphearthstone.data.remote.HearthstoneModel
import com.example.apphearthstone.repository.Repository
import com.example.apphearthstone.repository.RepositoryHearthstone
import com.example.apphearthstone.state.HearthstoneState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class ViewModelDetailsCard @Inject constructor(
    private val repository: Repository
) : ViewModel() {


    private var _result = MutableLiveData<HearthstoneState<List<HearthstoneModel>>>()
    val result : LiveData<HearthstoneState<List<HearthstoneModel>>> = _result

    fun serchSingleCard(cardId: String) {
        viewModelScope.launch {
            _result.value = HearthstoneState.Loading()
            try {
                val searchCard = repository.serchSingleCard(cardId)
                _result.value = setResultState(searchCard)
            } catch (e : Exception) {
                when(e) {
                    is IOException -> _result.value = HearthstoneState.Error("Nao foi possivel fazer a conecxão")
                    else -> _result.value = HearthstoneState.Error("Nao foi possivel fazer a conecxão")
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