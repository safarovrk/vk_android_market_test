package com.example.vk_android_market_intern.domian

import com.example.vk_android_market_intern.domian.pokemon_details.Pokemon as DetailsPokemon
import com.example.vk_android_market_intern.domian.pokemon_list.Pokemon as ListPokemon

interface PokemonRepository {
    suspend fun getPokemonsList(): List<ListPokemon>
    suspend fun getPokemon(name: String): DetailsPokemon
}