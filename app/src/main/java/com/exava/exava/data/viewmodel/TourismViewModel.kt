package com.exava.exava.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exava.exava.data.model.Tourism
import com.exava.exava.data.repository.TourismRepository
import kotlinx.coroutines.launch

class TourismViewModel(private val repository: TourismRepository): ViewModel() {
    private var _listTourism: MutableLiveData<List<Tourism>> = MutableLiveData<List<Tourism>>()
    val listTourism: LiveData<List<Tourism>> = _listTourism

    fun loadListTourism() {
        viewModelScope.launch {
            val value = repository.getAll()
            _listTourism.value = value.data
        }
    }
}