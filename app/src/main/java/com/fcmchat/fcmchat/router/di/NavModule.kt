package com.fcmchat.fcmchat.router.di

import com.fcmchat.fcmchat.router.presentation.presenter.INavPresenter
import com.fcmchat.fcmchat.router.presentation.presenter.NavPresenter
import com.fcmchat.fcmchat.watcher.interactor.IWatcherInteractor
import com.fcmchat.fcmchat.watcher.interactor.WatcherInteractor
import com.fcmchat.fcmchat.watcher.presenter.IWatcherPresenter
import com.fcmchat.fcmchat.watcher.presenter.WatcherPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by Voronin Igor on 03.08.2018.
 */
@Module()
class NavModule {
    @Provides fun createPresenter(): INavPresenter = NavPresenter()
    @Provides fun createWatcherPresenter(): IWatcherPresenter = WatcherPresenter()
    @Provides fun createInteractor(): IWatcherInteractor = WatcherInteractor()
}