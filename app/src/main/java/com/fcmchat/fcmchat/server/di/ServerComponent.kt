package com.fcmchat.server.di

import com.fcmchat.server.db.repo.TransactionRepo
import com.fcmchat.server.watcher.interactor.WatcherInteractor
import com.fcmchat.server.watcher.presenter.WatcherPresenter
import dagger.Component

/**
 * Created by Voronin Igor on 13.08.2018.
 */
@Component(modules = [ServerModule::class])
interface ServerComponent {

    fun inject(watcherPresenter: WatcherPresenter)
    fun inject(watcherInteractor: WatcherInteractor)
    fun inject(transactionRepo: TransactionRepo)

}