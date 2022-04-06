package com.example.jinglepay.data.repositories

import com.example.jinglepay.data.network.JinglePayApis
import com.example.jinglepay.data.network.SafeApiRequest
import com.example.jinglepay.data.network.models.MapPin
import javax.inject.Inject

class HomeRepository @Inject constructor(private val api: JinglePayApis) : SafeApiRequest() {

    suspend fun getMapPins(): ArrayList<MapPin> {
        return apiRequest {
            api.getMapPins()
        }
    }
}