package com.exava.exava.data.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.exava.exava.data.model.Tourism
import com.exava.exava.data.network.body.TourismRating
import com.exava.exava.data.repository.TourismRepository
import kotlinx.coroutines.launch

class TourismViewModel(private val repository: TourismRepository): ViewModel() {
    private var _listTourism: MutableLiveData<List<Tourism>> = MutableLiveData<List<Tourism>>()
    val listTourism: LiveData<List<Tourism>> = _listTourism
    private var _itemTourism: MutableLiveData<Tourism> = MutableLiveData<Tourism>()
    val itemTourism: LiveData<Tourism> = _itemTourism
    private var _searchResult: MutableLiveData<List<Tourism>> = MutableLiveData<List<Tourism>>()
    val searchResult: LiveData<List<Tourism>> = _searchResult
    private var _ratingTourism: MutableLiveData<TourismRating> = MutableLiveData<TourismRating>()
    val ratingTourism: LiveData<TourismRating> = _ratingTourism

    fun loadListTourism() {
        viewModelScope.launch {
            val value = repository.getAll()
            _listTourism.value = value.data
        }
    }

    fun loadItemTourism(id: Int) {
        viewModelScope.launch {
            val value = repository.getTourismById(id)
            _itemTourism.value = value.data
        }
    }

    fun loadSearch(query: String) {
        viewModelScope.launch {
            kotlin.runCatching {
                val value = repository.searchTourism(query)
                _searchResult.value = value.data
            }.onFailure {
                Log.e("ERROR_SEARCH", "error search")
            }
        }
    }

    fun loadRating(id: Int) {
        viewModelScope.launch {
            kotlin.runCatching {
                val value = repository.getTourismRating(id)
                _ratingTourism.value = value.data
            }.onFailure {
                Log.e("ERROR_SEARCH", "error rating")
            }
        }
    }
}