package com.ibrahim.basicboilerplate.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ibrahim.basicboilerplate.service.repository.BasicRepository
import com.ibrahim.basicboilerplate.util.Resource
import javax.inject.Inject

class LoginViewModel @Inject constructor(val repository: BasicRepository) : ViewModel() {

    val emailLiveData: MutableLiveData<String> = MutableLiveData()
    val passwordLiveData: MutableLiveData<String> = MutableLiveData()


    fun doLogin(): MutableLiveData<Resource> {
        return repository.doSignIn(emailLiveData.value!!, passwordLiveData.value!!)
    }
}