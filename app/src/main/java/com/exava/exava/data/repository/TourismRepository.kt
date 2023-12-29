package com.exava.exava.data.repository

import com.exava.exava.data.database.dao.TourismDao
import com.exava.exava.data.model.Tourism
import com.exava.exava.data.model.TourismItemResponse
import com.exava.exava.data.model.TourismListResponse
import com.exava.exava.data.network.body.TourismLoginResponse
import com.exava.exava.data.network.body.TourismProfileResponse
import com.exava.exava.data.network.body.TourismRatingResponse
import com.exava.exava.data.network.body.TourismRegisterResponse
import com.exava.exava.data.network.service.TourismAPIService

class TourismRepository(val token: String) {
    private val tourismService = TourismAPIService.getInstance(token)

    suspend fun getAll(): TourismListResponse {
        return tourismService.getAll()
    }

    suspend fun getTourismById(id: Int): TourismItemResponse {
        return tourismService.getTourismById(id)
    }

    suspend fun searchTourism(query: String): TourismListResponse {
        return tourismService.searchTourism(query)
    }

    suspend fun getTourismRating(id: Int): TourismRatingResponse {
        return tourismService.getTourismRating(id)
    }

    suspend fun profile(): TourismProfileResponse {
        return tourismService.profile()
    }
}

class TourismAuthRepository() {
    private val tourismAuthService = TourismAPIService.getInstance()

    suspend fun login(email: String, password: String): TourismLoginResponse {
        return tourismAuthService.login(email, password)
    }

    suspend fun register(username: String, email: String, password: String, name: String): TourismRegisterResponse {
        return tourismAuthService.register(username, email, password, name)
    }
}