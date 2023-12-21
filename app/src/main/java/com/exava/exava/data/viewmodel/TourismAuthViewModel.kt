package com.exava.exava.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exava.exava.data.network.body.TourismLoginResponse
import com.exava.exava.data.repository.TourismAuthRepository
import kotlinx.coroutines.launch

class TourismAuthViewModel(private val tourismAuthRepository: TourismAuthRepository): ViewModel() {
    private var _loginResponse: MutableLiveData<Result<TourismLoginResponse>> = MutableLiveData<Result<TourismLoginResponse>>()
    val loginResponse: LiveData<Result<TourismLoginResponse>> = _loginResponse

    suspend fun login(email: String, password: String): Result<TourismLoginResponse> {
        return kotlin.runCatching { tourismAuthRepository.login(email, password) }
    }
}