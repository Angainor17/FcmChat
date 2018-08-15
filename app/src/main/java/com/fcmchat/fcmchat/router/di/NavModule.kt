package com.fcmchat.fcmchat.router.di

import com.fcmchat.fcmchat.router.presentation.presenter.INavPresenter
import com.fcmchat.fcmchat.router.presentation.presenter.NavPresenter
import com.fcmchat.fcmchat.server.watcher.presenter.IWatcherPresenter
import com.fcmchat.server.di.ServerInjector
import dagger.Module
import dagger.Provides

/**
 * Created by Voronin Igor on 03.08.2018.
 */
@Module()
class NavModule {
    @Provides fun createPresenter(): INavPresenter = NavPresenter()
    @Provides fun createWatcherPresenter(): IWatcherPresenter = ServerInjector.createPresenter()
}