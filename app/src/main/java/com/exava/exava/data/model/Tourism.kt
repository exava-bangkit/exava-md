package com.exava.exava.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Tourism(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val placeName: String,
    val description: String,
    val categoryId: Int,
    val cityId: Int,
    val price: Int,
    val rating: Int,
    val lat: Double,
    val long: Double,
)
