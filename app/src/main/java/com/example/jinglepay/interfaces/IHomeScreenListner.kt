package com.example.jinglepay.interfaces

import com.example.jinglepay.data.network.models.MapPin

interface IHomeScreenListner {
    fun onMapPinClicked()
    fun onStarted()
    fun onSuccess(result: ArrayList<MapPin>)
    fun onFailure(message : String)
}