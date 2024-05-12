package com.example.vk_android_market_intern.data.response

import com.example.vk_android_market_intern.domian.pokemon_details.Pokemon
import com.google.gson.annotations.SerializedName

class ResponsePokemon(
    @SerializedName("name") val name: String,
    @SerializedName("height") val height: Int,
    @SerializedName("weight") val weight: Int,
    @SerializedName("stats") val stats: List<Stats>,
    @SerializedName("sprites") var sprites: Sprites
) {
    class Stats(
        @SerializedName("base_stat") val baseStat: Int,
        @SerializedName("effort") val effort: Int,
        @SerializedName("stat") val stat: Stat
    ) {
        class Stat(
            @SerializedName("name") val name: String,
            @SerializedName("url") val url: String
        )
    }
    class Sprites(
        @SerializedName("front_default") val frontDefault: String,
        @SerializedName("back_default") val backDefault: String
    )
}

fun ResponsePokemon.toDomain(): Pokemon {
    var statsString: String = ""
    this.stats.forEachIndexed { i, stat ->
        statsString += "${stat.stat.name}(${stat.baseStat})"
        if (i == stats.size - 1) statsString += "."
        else statsString += ", "
    }
    return Pokemon(
        name = this.name,
        height = this.height,
        weight = this.weight,
        frontSprite = this.sprites.frontDefault,
        backSprite = this.sprites.backDefault,
        stats = statsString
    )
}