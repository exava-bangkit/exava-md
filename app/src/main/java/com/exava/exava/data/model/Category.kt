package com.exava.exava.data.model

data class Category(
    val id: Int = 0,
    val nama: String,
)

fun getAllCategory(): List<Category> {
    return listOf(
        Category(0, "Bahari"),
        Category(1, "Budaya"),
        Category(2, "Cagar Alam"),
        Category(3, "Pusat Perbelanjaan"),
        Category(4, "Taman Hiburan"),
        Category(5, "Tempat Ibadah"),
    )
}
