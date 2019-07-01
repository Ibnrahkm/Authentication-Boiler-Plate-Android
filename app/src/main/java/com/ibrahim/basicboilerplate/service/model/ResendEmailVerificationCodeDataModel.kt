package com.ibrahim.basicboilerplate.service.model

import com.google.gson.annotations.SerializedName

data class ResendEmailVerificationCodeDataModel(

	@field:SerializedName("data")
	val data: Data? = null,

	@field:SerializedName("success")
	val success: Boolean? = null,

	@field:SerializedName("message")
	val message: String? = null
)