package com.example.vk_android_market_intern.domian.pokemon_list

import com.example.vk_android_market_intern.domian.PokemonRepository
import javax.inject.Inject

class GetPokemonsListUseCase @Inject constructor(
    private val pokemonRepository: PokemonRepository
) {
    suspend fun execute(): List<Pokemon> {
        return pokemonRepository.getPokemonsList()
    }
}