package com.fcmchat.fcmchat.app.di

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router


/**
 * Created by Voronin Igor on 31.07.2018.
 */
@Module()
class AppModule(private val context: Context) {

    @Provides fun context() = context

    @Provides fun getNavigatorHolder(): NavigatorHolder = cicerone.navigatorHolder

    @Provides fun getRouter(): Router = cicerone.router

    private val cicerone: Cicerone<Router> = Cicerone.create()

}