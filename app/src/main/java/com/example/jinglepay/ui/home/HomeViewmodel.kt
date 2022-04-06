package com.example.jinglepay.ui.home

import androidx.lifecycle.*
import com.example.jinglepay.data.network.models.MapPin
import com.example.jinglepay.data.repositories.HomeRepository
import com.example.jinglepay.interfaces.IHomeScreenListner
import com.example.jinglepay.utils.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import javax.inject.Inject


@HiltViewModel
class HomeViewmodel @Inject constructor(
    private val homeRepository: HomeRepository,
) : ViewModel() {

    var iHomeScreenListner: IHomeScreenListner? = null
    var mapPinsliveData: MutableLiveData<ArrayList<MapPin>> = MutableLiveData()
    var mapPins = ArrayList<MapPin>()
    var job: Job? = null
    var selectedMapPin: MapPin ?= null


    fun getMapPins() {
        job = Coroutines.launchPeriodicAsync(10000) {
            try {
                val response = homeRepository.getMapPins()
                mapPins.addAll(response)
                mapPinsliveData.postValue(mapPins)
                iHomeScreenListner?.onSuccess(mapPins)
                return@launchPeriodicAsync
            } catch (e: ApiException) {
                iHomeScreenListner?.onFailure(e.message!!)
            } catch (e: NoInternetConnectionException) {
                iHomeScreenListner?.onFailure(e.message!!)
            } catch (e: Exception) {
                iHomeScreenListner?.onFailure(e.message!!)
            }
        }
    }

    fun onMapPinClicked(mapPin: MapPin?) {
        selectedMapPin = mapPin
        iHomeScreenListner?.onMapPinClicked()
    }

    override fun onCleared() {
        job?.cancel()
        super.onCleared()
    }
}