package com.exava.exava.data.network.service

import com.exava.exava.data.network.endpoint.TourismAuthEndpoint
import com.exava.exava.data.network.endpoint.TourismEndpoint
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TourismAPIService {

    companion object {
        @Volatile
        private var instance: TourismEndpoint? = null
        @Volatile
        private var authInstance: TourismAuthEndpoint? = null

        private fun authClient(token: String): OkHttpClient {
            val client = OkHttpClient.Builder()
            val interceptor = Interceptor {
                val request = it.request()
                    .newBuilder()
                    .addHeader("Authorization", token)
                    .build()
                it.proceed(request)
            }
            client.addInterceptor(interceptor)
            return client.build()
        }
        @JvmStatic
        fun getInstance(token: String): TourismEndpoint {
            return instance ?: synchronized(this) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://exava-ch2-ps503-408514.et.r.appspot.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(authClient(token))
                    .build()
                instance = retrofit.create(TourismEndpoint::class.java)
                return requireNotNull(instance)
            }
        }

        @JvmStatic
        fun getInstance(): TourismAuthEndpoint {
            return authInstance ?: synchronized(this) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://exava-ch2-ps503-408514.et.r.appspot.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                authInstance = retrofit.create(TourismAuthEndpoint::class.java)
                return requireNotNull(authInstance)
            }
        }
    }
}