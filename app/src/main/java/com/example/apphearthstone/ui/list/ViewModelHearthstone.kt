package com.example.apphearthstone.ui.list

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
import javax.inject.Inject

@HiltViewModel
class ViewModelHearthstone @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _results = MutableLiveData<HearthstoneState<List<HearthstoneModel>>>()
    val results  : LiveData<HearthstoneState<List<HearthstoneModel>>> = _results

    fun getCardFromClass(cardClass : String) {
        viewModelScope.launch {
            _results.value = HearthstoneState.Loading()
            try {
                val response = repository.getCardFromClass(cardClass)
                _results.value = setResultState(response)
            } catch (e : Exception) {
                when(e) {
                    is IOException -> _results.value = HearthstoneState.Error("Nao foi possivel fazer a conecxão")
                    else -> _results.value = HearthstoneState.Error("Nao foi possivel fazer a conecxão")
                }
            }
        }
    }

    private fun setResultState(searchList: Response<List<HearthstoneModel>>): HearthstoneState<List<HearthstoneModel>> {
        if (searchList.isSuccessful) {
            searchList.body()?.let {
                return HearthstoneState.Success(it)
            }
        }
        return HearthstoneState.Error(searchList.message())
    }
}