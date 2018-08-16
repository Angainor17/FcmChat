package com.fcmchat.fcmchat.app

import android.support.multidex.MultiDexApplication
import com.fcmchat.fcmchat.app.di.Injector
import com.fcmchat.server.FcmServerFactory


/**
 * Created by Voronin Igor on 23.07.2018.
 */
class App : MultiDexApplication() {

    companion object {
        lateinit var injector: Injector
    }

    override fun onCreate() {
        FcmServerFactory.init(this)
        injector = Injector(this)
        super.onCreate()

    }
}