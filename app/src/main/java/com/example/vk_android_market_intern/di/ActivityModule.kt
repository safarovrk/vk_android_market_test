package com.example.vk_android_market_intern.di

import com.example.vk_android_market_intern.presentation.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [ViewModelModule::class])
abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun provideMainActivity(): MainActivity
}