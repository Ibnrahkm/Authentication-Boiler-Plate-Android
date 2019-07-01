package com.ibrahim.basicboilerplate.service.model

import com.google.gson.annotations.SerializedName


data class UserSettings(

	@field:SerializedName("gauth_enabled")
	val gauthEnabled: Int? = null,

	@field:SerializedName("updated_at")
	val updatedAt: String? = null,

	@field:SerializedName("user_id")
	val userId: Int? = null,

	@field:SerializedName("default_2fa")
	val default2fa: Any? = null,

	@field:SerializedName("created_at")
	val createdAt: String? = null,

	@field:SerializedName("two_fa_enabled_at_login")
	val twoFaEnabledAtLogin: Int? = null,

	@field:SerializedName("language")
	val language: String? = null,

	@field:SerializedName("currency")
	val currency: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)