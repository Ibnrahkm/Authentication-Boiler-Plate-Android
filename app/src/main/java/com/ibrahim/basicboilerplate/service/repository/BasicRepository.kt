package com.ibrahim.basicboilerplate.service.repository

import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.ibrahim.basicboilerplate.service.APIInterface
import com.ibrahim.basicboilerplate.service.model.ErrorModel
import com.ibrahim.basicboilerplate.service.model.ResendEmailVerificationCodeDataModel
import com.ibrahim.basicboilerplate.service.model.UserModel
import com.ibrahim.basicboilerplate.util.Resource
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class BasicRepository @Inject constructor(val apiInterface: APIInterface) {


    fun doSignIn(email: String, password: String): MutableLiveData<Resource> {
        val status: MutableLiveData<Resource> = MutableLiveData()
        status.value = Resource.loading(null)
        apiInterface.doLogin(email, password).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                val jObject = JSONObject(response.body())
                if (jObject.getBoolean("success")) {
                    status.value = Resource.success(Gson().fromJson(response.body(), UserModel::class.java))
                } else {
                    status.value = Resource.error<ErrorModel>(ErrorModel(response.code(), jObject.getString("message")))
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                status.value = Resource.error<ErrorModel>(ErrorModel(0, t.message.toString()))
            }
        })
        return status
    }


    fun doEmailVerification(code: String): MutableLiveData<Resource> {
        val status: MutableLiveData<Resource> = MutableLiveData()
        status.value = Resource.loading(null)
        apiInterface.doEmailVerify(code).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                if (response.code() == 401) {
                    status.value =
                        Resource.error<ErrorModel>(ErrorModel(401, JSONObject(response.errorBody()!!.string()).getString("message")))
                }else{
                    val jObject = JSONObject(response.body())
                    if (jObject.getBoolean("success")) {
                        status.value = Resource.success(Gson().fromJson(response.body(), UserModel::class.java))
                    } else {
                        status.value = Resource.error<ErrorModel>(ErrorModel(response.code(), jObject.getString("message")))
                    }
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                status.value = Resource.error<ErrorModel>(ErrorModel(0, t.message.toString()))
            }
        })
        return status
    }


    fun doResendEmailVerificationCode(email: String): MutableLiveData<Resource> {
        val resource = MutableLiveData<Resource>();
        resource.value = Resource.loading(null)
        apiInterface.doResendCode(email).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                val objects = JSONObject(response.body());
                if (objects.getBoolean("success")) {
                    resource.value = Resource.success(
                        Gson().fromJson(
                            response.body(),
                            ResendEmailVerificationCodeDataModel::class.java
                        )
                    )
                } else {
                    resource.value =
                        Resource.error<ErrorModel>(ErrorModel(response.code(), objects.getString("message")));
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                resource.value = Resource.error<ErrorModel>(ErrorModel(0, t.message.toString()))

            }
        })
        return resource
    }


    fun doSignup(
        email: String,
        password: String,
        firstName: String,
        lastName: String,
        confirmPassword: String
    ): MutableLiveData<Resource> {
        val status: MutableLiveData<Resource> = MutableLiveData()
        status.value = Resource.loading(null)
        apiInterface.doSignup(email, password, confirmPassword, firstName, lastName).enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                val jObject = JSONObject(response.body())
                if (jObject.getBoolean("success")) {
                    status.value = Resource.success(Gson().fromJson(response.body(), UserModel::class.java))
                } else {
                    status.value = Resource.error<ErrorModel>(ErrorModel(response.code(), jObject.getString("message")))
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                status.value = Resource.error<ErrorModel>(ErrorModel(0, t.message.toString()))
            }
        })
        return status
    }
}