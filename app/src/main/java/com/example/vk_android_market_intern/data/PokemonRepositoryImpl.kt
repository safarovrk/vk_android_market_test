package com.example.vk_android_market_intern.data

import com.example.vk_android_market_intern.data.response.toDomain
import com.example.vk_android_market_intern.domian.PokemonRepository
import javax.inject.Inject
import com.example.vk_android_market_intern.domian.pokemon_list.Pokemon as ListPokemon
import com.example.vk_android_market_intern.domian.pokemon_details.Pokemon as DetailsPokemon

class PokemonRepositoryImpl @Inject constructor(
    private val pokemonApi: PokemonApi
) : PokemonRepository {
    override suspend fun getPokemonsList(): List<ListPokemon> {
        val pokemonsList = pokemonApi.getPokemonsList()
        val resultPokemonsList: MutableList<ListPokemon> = mutableListOf()
        pokemonsList.results.forEach {
            resultPokemonsList.add(
                ListPokemon(
                    it.name,
                    pokemonApi.getPokemon(it.name).sprites.frontDefault
                )
            )
        }
        return resultPokemonsList
    }

    override suspend fun getPokemon(name: String): DetailsPokemon {
        return pokemonApi.getPokemon(name).toDomain()
    }
}