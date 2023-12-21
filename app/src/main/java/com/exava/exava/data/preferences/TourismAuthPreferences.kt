package com.exava.exava.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesOf
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "auth")

class TourismAuthPreferences(private val dataStore: DataStore<Preferences>) {
    private val AUTH_TOKEN = stringPreferencesKey("auth_token")

    fun getAuthToken(): Flow<String> {
        return dataStore.data.map {
            it[AUTH_TOKEN] ?: ""
        }
    }

    suspend fun setAuthToken(token: String) {
        dataStore.edit {
            it[AUTH_TOKEN] = token
        }
    }

    companion object {
        @Volatile
        private var instance: TourismAuthPreferences? = null

        fun getInstance(dataStore: DataStore<Preferences>): TourismAuthPreferences {
            return instance ?: synchronized(this) {
                instance = TourismAuthPreferences(dataStore)
                return requireNotNull(instance)
            }
        }
    }
}