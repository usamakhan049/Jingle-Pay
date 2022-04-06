package com.example.jinglepay.data.network

import com.example.jinglepay.data.network.models.MapPin
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface JinglePayApis {

    @GET("users")
    suspend fun getMapPins(): Response<ArrayList<MapPin>>

    companion object {
        private val BASE_URL = "https://jsonplaceholder.typicode.com/"

        operator fun invoke(networkConnectionInterceptor: NetworkConnectionInterceptor): JinglePayApis {
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(JinglePayApis::class.java)
        }
    }
}