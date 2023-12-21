package com.exava.exava.data.network.body

import com.google.gson.annotations.SerializedName

data class TourismRegisterResponse(

	@field:SerializedName("data")
	val registerData: RegisterData? = null,

	@field:SerializedName("message")
	val message: String? = null
)

data class RegisterData(

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)
