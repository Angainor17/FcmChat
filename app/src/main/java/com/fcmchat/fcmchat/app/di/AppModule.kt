package com.fcmchat.fcmchat.app.di

import android.content.Context
import com.fcmchat.fcmchat.server.watcher.interactor.IWatcherInteractor
import com.fcmchat.server.di.ServerInjector
import com.fcmchat.server.fcm.repo.IFcmRepo
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

    private val cicerone: Cicerone<Router> = Cicerone.create()
    private var fcmRepo: IFcmRepo = ServerInjector.createFcmServer()
    private var watcherInteractor: IWatcherInteractor = ServerInjector.createInteractor()

    @Provides fun context() = context
    @Provides fun getNavigatorHolder(): NavigatorHolder = cicerone.navigatorHolder
    @Provides fun getRouter(): Router = cicerone.router
    @Provides fun getFcmRepository(): IFcmRepo = fcmRepo
    @Provides fun getWatcherInteractor(): IWatcherInteractor = watcherInteractor

}