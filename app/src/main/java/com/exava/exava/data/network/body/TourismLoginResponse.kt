package com.exava.exava.data.network.body

import com.google.gson.annotations.SerializedName

data class TourismLoginResponse(

	@field:SerializedName("message")
	val message: String? = null,

	@field:SerializedName("token")
	val token: String? = null
)
