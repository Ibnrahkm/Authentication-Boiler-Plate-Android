package com.ibrahim.basicboilerplate.dagger.Module

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

@Module
class ContextModule @Inject constructor(val context: Context) {

    @Provides
    @Singleton
    fun provideAppContext(): Context {
        return context
    }

}