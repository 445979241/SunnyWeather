package com.hsh.sunnyweather.ui.place

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.hsh.sunnyweather.logic.Repository
import com.hsh.sunnyweather.logic.model.Place

class PlaceViewModel:ViewModel() {

    private val searchLiveData=MutableLiveData<String>()

    val placeList=ArrayList<Place>()

    val placeLiveData=Transformations.switchMap(searchLiveData){query->
        val a =Repository.searchPlaces(query)
        return@switchMap a
    }

    fun searchPlaces(query:String){
        searchLiveData.value=query
    }

    fun savePlace(place: Place) = Repository.savePlace(place)

    fun getSavedPlace() = Repository.getSavedPlace()
    fun isPlaceSaved() = Repository.isPlaceSaved()

}