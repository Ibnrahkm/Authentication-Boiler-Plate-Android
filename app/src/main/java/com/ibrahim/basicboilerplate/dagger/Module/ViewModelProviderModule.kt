package com.ibrahim.basicboilerplate.dagger.Module

import androidx.lifecycle.ViewModelProvider
import com.ibrahim.basicboilerplate.service.repository.BasicRepository
import com.ibrahim.basicboilerplate.viewmodel.*
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelProviderModule {

    @Binds
    abstract fun provideViewModelFactory(factory: ViewModelProviderFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelMappingKey(LoginViewModel::class)
    abstract fun provideLoginViewModel(viewModel: LoginViewModel): LoginViewModel


    @Binds
    @IntoMap
    @ViewModelMappingKey(SignupViewModel::class)
    abstract fun provideSignupViewModel(viewModel: SignupViewModel): SignupViewModel

    @Binds
    @IntoMap
    @ViewModelMappingKey(EmailVerificationViewModel::class)
    abstract fun provideEmailVerificationViewModel(viewModel: EmailVerificationViewModel): EmailVerificationViewModel


}