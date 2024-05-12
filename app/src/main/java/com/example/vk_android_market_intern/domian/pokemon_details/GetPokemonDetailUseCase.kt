package com.example.vk_android_market_intern.domian.pokemon_details

import com.example.vk_android_market_intern.domian.PokemonRepository
import javax.inject.Inject

class GetPokemonDetailUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {
    suspend fun execute(name: String): Pokemon {
        return pokemonRepository.getPokemon(name)
    }
}