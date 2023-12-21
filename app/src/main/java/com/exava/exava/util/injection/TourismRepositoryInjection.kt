package com.exava.exava.util.injection

import android.content.Context
import com.exava.exava.data.preferences.TourismAuthPreferences
import com.exava.exava.data.preferences.dataStore
import com.exava.exava.data.repository.TourismRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

object TourismRepositoryInjection {
    fun provideRepository(context: Context): TourismRepository {
        val dataStore = context.dataStore
        val pref = TourismAuthPreferences.getInstance(dataStore)
        val token = runBlocking { pref.getAuthToken().first() }
        if (token != "") {
            return TourismRepository(token)
        } else {
            throw IllegalAccessException("Token masih kosong!")
        }
    }
}