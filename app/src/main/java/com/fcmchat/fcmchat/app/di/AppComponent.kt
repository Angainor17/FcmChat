package com.fcmchat.fcmchat.app.di

import android.content.Context
import dagger.Component
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

/**
 * Created by Voronin Igor on 31.07.2018.
 */
@Component(modules = [AppModule::class])
interface AppComponent {

    fun context(): Context

    fun getNavigatorHolder(): NavigatorHolder

    fun getRouter(): Router

}