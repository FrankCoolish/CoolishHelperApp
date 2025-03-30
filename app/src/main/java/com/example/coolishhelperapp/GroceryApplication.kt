package com.example.coolishhelperapp

import android.app.Application
import com.example.coolishhelperapp.data.AppContainer
import com.example.coolishhelperapp.data.AppDataContainer

class GroceryApplication : Application() {

    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}