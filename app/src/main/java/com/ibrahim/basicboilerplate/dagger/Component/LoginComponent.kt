package com.ibrahim.basicboilerplate.dagger.Component

import com.ibrahim.basicboilerplate.dagger.Module.LoginModule
import com.ibrahim.basicboilerplate.dagger.Module.ViewModelProviderModule
import com.ibrahim.basicboilerplate.dagger.Scope.ActivityScope
import com.ibrahim.basicboilerplate.view.fragment.LoginFragment
import dagger.Component

@ActivityScope
@Component(modules = arrayOf(LoginModule::class,ViewModelProviderModule::class), dependencies = arrayOf(AppComponent::class))
interface LoginComponent {
    fun inject(fragment: LoginFragment)

}