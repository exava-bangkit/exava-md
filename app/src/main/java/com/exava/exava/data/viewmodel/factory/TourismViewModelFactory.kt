package com.exava.exava.data.viewmodel.factory

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.exava.exava.data.repository.TourismAuthRepository
import com.exava.exava.data.repository.TourismRepository
import com.exava.exava.data.viewmodel.TourismAuthViewModel
import com.exava.exava.data.viewmodel.TourismViewModel

class TourismViewModelFactory(private val tourismRepository: TourismRepository): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TourismViewModel::class.java)) {
            return TourismViewModel(tourismRepository) as T
        }
        throw IllegalArgumentException("No ViewModel class : ${modelClass.name}")
    }
}