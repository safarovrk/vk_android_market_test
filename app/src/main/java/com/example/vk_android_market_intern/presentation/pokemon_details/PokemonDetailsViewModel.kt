package com.example.vk_android_market_intern.presentation.pokemon_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vk_android_market_intern.data.response.ResponseStates
import com.example.vk_android_market_intern.domian.pokemon_details.GetPokemonDetailUseCase
import com.example.vk_android_market_intern.domian.pokemon_details.Pokemon
import kotlinx.coroutines.launch
import javax.inject.Inject

class PokemonDetailsViewModel @Inject constructor(
    private val getPokemonDetailUseCase: GetPokemonDetailUseCase
) : ViewModel() {
    private val _detailState = MutableLiveData<ResponseStates<Pokemon>>()
    val detailState: LiveData<ResponseStates<Pokemon>> = _detailState
    val currentPokemonName = MutableLiveData<String>()

    fun onLoadData(name: String) = viewModelScope.launch {
        _detailState.value = ResponseStates.Loading()
        try {
            _detailState.value = ResponseStates.Success(getPokemonDetailUseCase.execute(name))
        } catch (e: Exception) {
            _detailState.value = ResponseStates.Failure(e)
        }
    }
}