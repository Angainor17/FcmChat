package com.fcmchat.fcmchat

import android.app.Application
import com.fcmchat.server.FcmServerFactory

/**
 * Created by Voronin Igor on 23.07.2018.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        FcmServerFactory.init(this)
    }
}