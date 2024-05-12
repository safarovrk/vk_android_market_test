package com.example.vk_android_market_intern.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.vk_android_market_intern.presentation.pokemon_details.PokemonDetailsViewModel
import com.example.vk_android_market_intern.presentation.pokemon_list.PokemonListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(PokemonListViewModel::class)
    abstract fun signInViewModel(pokemonListViewModel: PokemonListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PokemonDetailsViewModel::class)
    abstract fun catalogViewModel(pokemonDetailsViewModel: PokemonDetailsViewModel): ViewModel
}