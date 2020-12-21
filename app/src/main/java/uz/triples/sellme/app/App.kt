package uz.triples.sellme.app

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

class App : Application() {

    companion object {
        lateinit var instance: Context
        lateinit var sharedPreferences: SharedPreferences
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        sharedPreferences = getSharedPreferences("mSharedPreference", 0)
    }

}