package com.example.vk_android_market_intern.data.response

import com.example.vk_android_market_intern.domian.pokemon_list.Pokemon
import com.google.gson.annotations.SerializedName

class ResponsePokemonsList (
    @SerializedName("results") var results: List<Results>
) {
    class Results(
        @SerializedName("name") var name: String,
        @SerializedName("url") var url: String
    )
}

