package com.ibrahim.basicboilerplate.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ibrahim.basicboilerplate.service.repository.BasicRepository
import com.ibrahim.basicboilerplate.util.Resource
import javax.inject.Inject

class SignupViewModel @Inject constructor(val repository: BasicRepository) : ViewModel() {

    val emailLiveData: MutableLiveData<String> = MutableLiveData()
    val passwordLiveData: MutableLiveData<String> = MutableLiveData()
    val firstNameLiveData: MutableLiveData<String> = MutableLiveData()
    val lastNameLiveData: MutableLiveData<String> = MutableLiveData()
    val confirmPasswordLiveData: MutableLiveData<String> = MutableLiveData()


    fun doSignup(): MutableLiveData<Resource> {
        return repository.doSignup(emailLiveData.value!!, passwordLiveData.value!!,firstNameLiveData.value!!,lastNameLiveData.value!!,confirmPasswordLiveData.value!!)
    }
}