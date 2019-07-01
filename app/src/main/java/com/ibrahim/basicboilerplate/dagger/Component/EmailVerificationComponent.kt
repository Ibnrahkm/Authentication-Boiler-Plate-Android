package com.ibrahim.basicboilerplate.dagger.Component

import com.ibrahim.basicboilerplate.dagger.Module.EmailVerificationModule
import com.ibrahim.basicboilerplate.dagger.Module.LoginModule
import com.ibrahim.basicboilerplate.dagger.Module.SignupModule
import com.ibrahim.basicboilerplate.dagger.Module.ViewModelProviderModule
import com.ibrahim.basicboilerplate.dagger.Scope.ActivityScope
import com.ibrahim.basicboilerplate.view.fragment.EmailVerificationFragment
import com.ibrahim.basicboilerplate.view.fragment.LoginFragment
import com.ibrahim.basicboilerplate.view.fragment.SignupFragment
import dagger.Component

@ActivityScope
@Component(modules = arrayOf(EmailVerificationModule::class,ViewModelProviderModule::class), dependencies = arrayOf(AppComponent::class))
interface EmailVerificationComponent {
    fun inject(fragment: EmailVerificationFragment)

}