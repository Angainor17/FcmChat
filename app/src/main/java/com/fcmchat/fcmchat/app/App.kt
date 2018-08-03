package com.fcmchat.fcmchat.app

import android.support.multidex.MultiDexApplication
import com.fcmchat.fcmchat.app.di.Injector
import com.fcmchat.server.FcmServerFactory
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router


/**
 * Created by Voronin Igor on 23.07.2018.
 */
class App : MultiDexApplication() {

    companion object {
        lateinit var injector: Injector
    }

    override fun onCreate() {
        super.onCreate()
        injector = Injector(this)
        FcmServerFactory.init(this)
    }
}