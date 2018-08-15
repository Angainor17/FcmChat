package com.fcmchat.server.di

import android.annotation.SuppressLint
import android.content.Context
import com.fcmchat.server.FcmServer
import com.fcmchat.server.FcmServerImpl
import com.fcmchat.server.fcm.repo.IFcmRepo
import com.fcmchat.server.watcher.interactor.IWatcherInteractor
import com.fcmchat.server.watcher.interactor.WatcherInteractor
import com.fcmchat.server.watcher.presenter.IWatcherPresenter
import com.fcmchat.server.watcher.presenter.WatcherPresenter

/**
 * Created by Voronin Igor on 16.07.2018.
 */
class ServerInjector {

    companion object {

        @SuppressLint("StaticFieldLeak") lateinit var context: Context
        lateinit var mainComponent: ServerComponent

        private val fcmServerImpl: FcmServer = FcmServerImpl()
        private var fcmServerRepo: IFcmRepo? = null

        @SuppressLint("StaticFieldLeak")
        private lateinit var serverModule: ServerModule

        fun init(context: Context) {
            Companion.context = context
            serverModule = ServerModule(context)

            mainComponent = DaggerServerComponent.builder()
                    .serverModule(serverModule)
                    .build()
        }

        fun createServer(): FcmServer = fcmServerImpl
        fun createFcmServer(): IFcmRepo = serverModule.getFcmRepo()
        fun createPresenter(): IWatcherPresenter = serverModule.getPresenterInstance()
        fun createInteractor(): IWatcherInteractor = serverModule.getInteractorInstance()
    }
}