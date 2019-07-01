package com.ibrahim.basicboilerplate.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ibrahim.basicboilerplate.service.repository.BasicRepository
import com.ibrahim.basicboilerplate.util.Resource
import javax.inject.Inject

class EmailVerificationViewModel @Inject constructor(val repository: BasicRepository) : ViewModel() {

    val codeLiveData: MutableLiveData<String> = MutableLiveData()

    fun doEmailVerification(): MutableLiveData<Resource> {
        return repository.doEmailVerification(codeLiveData.value!!)
    }


}