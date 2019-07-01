package com.ibrahim.basicboilerplate.service.model

import com.google.gson.annotations.SerializedName

data class UserInfo(

	@field:SerializedName("reset_token")
	val resetToken: Any? = null,

	@field:SerializedName("role")
	val role: Int? = null,

	@field:SerializedName("email_verified")
	val emailVerified: String? = null,

	@field:SerializedName("level")
	val level: Any? = null,

	@field:SerializedName("logged_in_with_2fa")
	val loggedInWith2fa: Int? = null,

	@field:SerializedName("last_name")
	val lastName: String? = null,

	@field:SerializedName("photo")
	val photo: String? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("logged_in_with_2fa_from_app")
	val loggedInWith2faFromApp: Int? = null,

	@field:SerializedName("country_code")
	val countryCode: Any? = null,

	@field:SerializedName("notification")
	val notification: Any? = null,

	@field:SerializedName("user_settings")
	val userSettings: UserSettings? = null,

	@field:SerializedName("active_status")
	val activeStatus: Int? = null,

	@field:SerializedName("phone_verified")
	val phoneVerified: String? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("phone")
	val phone: Any? = null,

	@field:SerializedName("google_code")
	val googleCode: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("first_name")
	val firstName: String? = null,

	@field:SerializedName("email")
	val email: String? = null
)