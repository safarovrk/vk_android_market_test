package com.example.vk_android_market_intern.presentation.pokemon_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vk_android_market_intern.data.response.ResponseStates
import com.example.vk_android_market_intern.domian.pokemon_list.GetPokemonsListUseCase
import com.example.vk_android_market_intern.domian.pokemon_list.Pokemon
import kotlinx.coroutines.launch
import javax.inject.Inject

class PokemonListViewModel @Inject constructor(
    private val getPokemonsListUseCase: GetPokemonsListUseCase
) : ViewModel() {
    private val _listState = MutableLiveData<ResponseStates<List<Pokemon>>>()
    val listState: LiveData<ResponseStates<List<Pokemon>>> = _listState

    init {
        onLoadData()
    }

    fun onLoadData() = viewModelScope.launch {
        _listState.value = ResponseStates.Loading()
        try {
            _listState.value = ResponseStates.Success(getPokemonsListUseCase.execute())
        } catch (e: Exception) {
            _listState.value = ResponseStates.Failure(e)
        }
    }
}