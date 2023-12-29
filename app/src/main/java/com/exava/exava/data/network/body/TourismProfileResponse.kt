package com.exava.exava.data.network.body

import com.google.gson.annotations.SerializedName

data class TourismProfileResponse(

	@field:SerializedName("data")
	val tourismProfile: TourismProfile,

	@field:SerializedName("message")
	val message: String
)

data class TourismProfile(

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("email")
	val email: String,

	@field:SerializedName("username")
	val username: String
)
