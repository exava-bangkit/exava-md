package com.exava.exava.data.viewmodel.factory

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.exava.exava.data.repository.TourismAuthRepository
import com.exava.exava.data.viewmodel.TourismAuthViewModel

class TourismAuthViewModelFactory(private val tourismAuthRepository: TourismAuthRepository): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TourismAuthViewModel::class.java)) {
            return TourismAuthViewModel(tourismAuthRepository) as T
        }
        throw IllegalArgumentException("No ViewModel class : ${modelClass.name}")
    }
}