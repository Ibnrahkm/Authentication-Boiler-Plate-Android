package com.ibrahim.basicboilerplate.dagger.Component

import android.content.Context
import com.ibrahim.basicboilerplate.AppController
import com.ibrahim.basicboilerplate.dagger.Module.ContextModule
import com.ibrahim.basicboilerplate.dagger.Module.RetrofitModule
import com.ibrahim.basicboilerplate.service.APIInterface
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = arrayOf(RetrofitModule::class, ContextModule::class))
interface AppComponent {
    fun provideInterface(): APIInterface
    fun provideAppContext():Context
    fun inject(controller: AppController)

}