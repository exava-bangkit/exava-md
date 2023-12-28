package com.exava.exava.data.network.body

import com.google.gson.annotations.SerializedName

data class TourismRatingResponse(

	@field:SerializedName("data")
	val data: TourismRating,

	@field:SerializedName("message")
	val message: String
)

data class TourismRating(

	@field:SerializedName("1")
	val jsonMember1: Int,

	@field:SerializedName("2")
	val jsonMember2: Int,

	@field:SerializedName("3")
	val jsonMember3: Int,

	@field:SerializedName("4")
	val jsonMember4: Int,

	@field:SerializedName("5")
	val jsonMember5: Int
)
