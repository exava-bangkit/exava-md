package com.exava.exava.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Tourism(
    @PrimaryKey(autoGenerate = true)
    @field:SerializedName("Place_Id")
    val id: Int = 0,
    @field:SerializedName("Place_Name")
    val placeName: String,
    @field:SerializedName("Description")
    val description: String,
    @field:SerializedName("Id_Category")
    val categoryId: Int,
    @field:SerializedName("Id_City")
    val cityId: Int,
    @field:SerializedName("Price")
    val price: Int,
    @field:SerializedName("Rating")
    val rating: Int,
    @field:SerializedName("Lat")
    val lat: Double,
    @field:SerializedName("Long")
    val long: Double,
    @field:SerializedName("Time_Minutes")
    val timeMinutes: Int? = null,
    @field:SerializedName("Coordinate")
    val coordinate: String,
)
data class TourismListResponse(

    @field:SerializedName("data")
    val data: List<Tourism>,

    @field:SerializedName("message")
    val message: String
)
data class TourismItemResponse(

    @field:SerializedName("data")
    val data: Tourism,

    @field:SerializedName("message")
    val message: String
)
