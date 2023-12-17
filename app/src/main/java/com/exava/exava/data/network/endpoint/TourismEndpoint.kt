package com.exava.exava.data.network.endpoint

import retrofit2.http.GET

interface TourismEndpoint {
    @GET("/tourism")
    suspend fun getAll()
}