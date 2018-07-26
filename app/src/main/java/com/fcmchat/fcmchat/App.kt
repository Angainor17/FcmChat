package com.fcmchat.fcmchat

import android.app.Application
import android.support.multidex.MultiDexApplication
import com.fcmchat.server.FcmServerFactory

/**
 * Created by Voronin Igor on 23.07.2018.
 */
class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        FcmServerFactory.init(this)
    }
}