package com.example.vk_android_market_intern.data

import com.example.vk_android_market_intern.data.response.ResponsePokemon
import com.example.vk_android_market_intern.data.response.ResponsePokemonsList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {
    @GET("pokemon")
    suspend fun getPokemonsList(): ResponsePokemonsList

    @GET("pokemon/{name}")
    suspend fun getPokemon(
        @Path("name") name: String
    ): ResponsePokemon
}