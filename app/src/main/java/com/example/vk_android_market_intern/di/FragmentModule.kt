package com.example.vk_android_market_intern.di

import com.example.vk_android_market_intern.presentation.pokemon_list.PokemonListFragment
import com.example.vk_android_market_intern.presentation.pokemon_details.PokemonDetailsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [ViewModelModule::class])
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun pokemonListFragment(): PokemonListFragment

    @ContributesAndroidInjector
    abstract fun pokemonDetailsFragment(): PokemonDetailsFragment
}