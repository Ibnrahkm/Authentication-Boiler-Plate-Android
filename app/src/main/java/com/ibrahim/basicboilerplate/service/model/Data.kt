package com.ibrahim.basicboilerplate.service.model

import com.google.gson.annotations.SerializedName

data class Data(

	@field:SerializedName("access_token")
	val accessToken: String? = null,

	@field:SerializedName("access_type")
	val accessType: String? = null,

	@field:SerializedName("user_info")
	val userInfo: UserInfo? = null,

	@field:SerializedName("is_google_auth_enabled")
	val isGoogleAuthEnabled: Boolean? = null
)