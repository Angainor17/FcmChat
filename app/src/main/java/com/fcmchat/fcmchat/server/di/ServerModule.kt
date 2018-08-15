package com.fcmchat.server.di

import android.arch.persistence.room.Room
import android.content.Context
import com.fcmchat.server.db.AppDatabase
import com.fcmchat.server.db.repo.ITransactionRepo
import com.fcmchat.server.db.repo.TransactionRepo
import com.fcmchat.server.fcm.repo.FcmRepo
import com.fcmchat.server.fcm.repo.IFcmRepo
import com.fcmchat.server.watcher.interactor.IWatcherInteractor
import com.fcmchat.server.watcher.interactor.WatcherInteractor
import com.fcmchat.server.watcher.presenter.IWatcherPresenter
import com.fcmchat.server.watcher.presenter.WatcherPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by Voronin Igor on 13.08.2018.
 */
@Module()
class ServerModule(private val context: Context) {

    private val db: AppDatabase = Room.databaseBuilder(context, AppDatabase::class.java, "database")
            .allowMainThreadQueries()
            .build()

    private val interactor: IWatcherInteractor = WatcherInteractor()
    private var transactRepo: ITransactionRepo = TransactionRepo()
    private var fcmRepo: ITransactionRepo = TransactionRepo()
    private var watcherPresenter: IWatcherPresenter = WatcherPresenter()


    @Provides fun getDb(): AppDatabase = db
    @Provides fun getContextInstance(): Context = context
    @Provides fun getInteractorInstance(): IWatcherInteractor = interactor
    @Provides fun getTransactRepo(): ITransactionRepo = transactRepo
    @Provides fun getFcmRepo(): IFcmRepo = FcmRepo(ServerInjector.context)
    @Provides fun getPresenterInstance(): IWatcherPresenter = watcherPresenter
}