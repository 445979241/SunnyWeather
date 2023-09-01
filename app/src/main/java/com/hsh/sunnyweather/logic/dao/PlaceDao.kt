package com.hsh.sunnyweather.logic.dao

import android.content.Context
import androidx.core.content.edit
import com.google.gson.Gson
import com.hsh.sunnyweather.SunnyWeatherApplication
import com.hsh.sunnyweather.logic.model.Place

object PlaceDao {
    fun savePlace(place:Place){
        sharedPreferences().edit{
            putString("place", Gson().toJson(place))
        }
    }

    fun getPlace(): Place {
        val placeJson = sharedPreferences().getString("place","")
        return Gson().fromJson(placeJson,Place::class.java)
    }


    private fun sharedPreferences()=SunnyWeatherApplication.context
        .getSharedPreferences("sunny_weather", Context.MODE_PRIVATE)

    fun isPlaceSaved():Boolean {
        return sharedPreferences().contains("place")
    }
}