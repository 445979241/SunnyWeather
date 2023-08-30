package com.hsh.sunnyweather

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class SunnyWeatherApplication : Application() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context

        const val TOKEN="n0pxoOK9dvzkqfgx"
    }
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}
