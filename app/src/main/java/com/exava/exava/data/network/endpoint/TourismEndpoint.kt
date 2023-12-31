package com.exava.exava.data.network.endpoint

import com.exava.exava.data.model.TourismItemResponse
import com.exava.exava.data.model.TourismListResponse
import com.exava.exava.data.network.body.TourismLoginResponse
import com.exava.exava.data.network.body.TourismProfileResponse
import com.exava.exava.data.network.body.TourismRatingResponse
import com.exava.exava.data.network.body.TourismRegisterResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface TourismEndpoint {
    @GET("/tourism")
    suspend fun getAll(): TourismListResponse

    @GET("/tourism/{id}")
    suspend fun getTourismById(@Path("id") id: Int): TourismItemResponse

    @GET("/tourism/{id}/rating")
    suspend fun getTourismRating(@Path("id") id: Int): TourismRatingResponse

    @FormUrlEncoded
    @POST("/tourism/search")
    suspend fun searchTourism(@Field("query") query: String): TourismListResponse

    @GET("/profile")
    suspend fun profile(): TourismProfileResponse
}

interface TourismAuthEndpoint {
    @FormUrlEncoded
    @POST("/login")
    suspend fun login(@Field("email") email: String, @Field("password") password: String): TourismLoginResponse

    @FormUrlEncoded
    @POST("/register")
    suspend fun register(@Field("username") username: String, @Field("email")email: String, @Field("password")password: String, @Field("name") name: String): TourismRegisterResponse
}