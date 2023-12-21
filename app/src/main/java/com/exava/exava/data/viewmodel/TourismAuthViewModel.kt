package com.exava.exava.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exava.exava.data.network.body.TourismLoginResponse
import com.exava.exava.data.network.body.TourismRegisterResponse
import com.exava.exava.data.repository.TourismAuthRepository
import kotlinx.coroutines.launch

class TourismAuthViewModel(private val tourismAuthRepository: TourismAuthRepository): ViewModel() {
    suspend fun login(email: String, password: String): Result<TourismLoginResponse> {
        return kotlin.runCatching { tourismAuthRepository.login(email, password) }
    }

    suspend fun register(username: String, email: String, password: String): Result<TourismRegisterResponse> {
        return kotlin.runCatching { tourismAuthRepository.register(username, email, password) }
    }
}