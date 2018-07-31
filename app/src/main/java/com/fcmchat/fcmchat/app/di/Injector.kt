package com.fcmchat.fcmchat.app.di

import com.fcmchat.fcmchat.app.App
import com.fcmchat.fcmchat.mainActivity.di.DaggerMainActivityComponent
import com.fcmchat.fcmchat.mainActivity.di.MainActivityComponent
import com.fcmchat.fcmchat.mainActivity.di.MainActivityModule

/**
 * Created by Voronin Igor on 31.07.2018.
 */
class Injector(app: App) {

    private val appComponent: AppComponent = DaggerAppComponent.builder().appModule(AppModule(app)).build()

    val mainActivityComponent: MainActivityComponent = DaggerMainActivityComponent.builder()
            .mainActivityModule(MainActivityModule())
            .appComponent(appComponent)
            .build()

}