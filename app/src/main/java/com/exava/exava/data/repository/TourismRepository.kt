package com.exava.exava.data.repository

import com.exava.exava.data.database.dao.TourismDao
import com.exava.exava.data.model.Tourism
import com.exava.exava.data.network.body.TourismLoginResponse
import com.exava.exava.data.network.service.TourismAPIService

class TourismRepository(val token: String) {
    private val tourismService = TourismAPIService.getInstance(token)


}

class TourismAuthRepository() {
    private val tourismAuthService = TourismAPIService.getInstance()

    suspend fun login(email: String, password: String): TourismLoginResponse {
        return tourismAuthService.login(email, password)
    }

    suspend fun register(username: String, email: String, password: String) {
        return tourismAuthService.register(username, email, password)
    }
}