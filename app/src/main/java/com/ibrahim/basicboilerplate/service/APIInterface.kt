package com.ibrahim.basicboilerplate.service

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface APIInterface {

    @FormUrlEncoded
    @POST("login")
    fun doLogin(@Field("email")email:String,@Field("password") password:String):Call<String>
    @FormUrlEncoded
    @POST("registration")
    fun doSignup(@Field("email")email:String,@Field("password") password:String,@Field("password_confirmation") confirmPassword:String,
                 @Field("first_name") firstName:String,@Field("last_name") lastName:String):Call<String>
    @FormUrlEncoded
    @POST("email-verify")
    fun doEmailVerify(@Field("email_verification")email:String):Call<String>

    @FormUrlEncoded
    @POST("resend-verification-email")
    fun doResendCode(@Field("email")email:String):Call<String>

}