package com.example.vk_android_market_intern.di

import com.example.vk_android_market_intern.TheApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        NetworkModule::class,
        ApplicationModule::class,
        ActivityModule::class,
        FragmentModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<TheApplication> {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: TheApplication): ApplicationComponent
    }
}