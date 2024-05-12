package com.example.vk_android_market_intern.di

import android.content.Context
import com.example.vk_android_market_intern.TheApplication
import dagger.Module
import dagger.Provides

@Module
open class ApplicationModule {

    @Provides
    fun provideApplicationContext(app: TheApplication): Context {
        return app.applicationContext
    }
}