package com.exava.exava.data.network.endpoint

import com.exava.exava.data.network.body.TourismLoginResponse
import com.exava.exava.data.network.body.TourismRegisterResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface TourismEndpoint {
    @GET("/tourism")
    suspend fun getAll()
}

interface TourismAuthEndpoint {
    @FormUrlEncoded
    @POST("/login")
    suspend fun login(@Field("email") email: String, @Field("password") password: String): TourismLoginResponse

    @FormUrlEncoded
    @POST("/register")
    suspend fun register(@Field("username") username: String, @Field("email")email: String, @Field("password")password: String): TourismRegisterResponse
}