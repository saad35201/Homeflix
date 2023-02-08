package com.saad.homeflix

import android.app.Application
import com.saad.homeflix.utils.NetworkConnectivityService
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HomeFlixApp : Application() {

    override fun onCreate() {
        super.onCreate()

        //init wifiService
        NetworkConnectivityService.instance.initializeWithApplicationContext(this)

    }

}