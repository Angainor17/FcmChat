package com.fcmchat.fcmchat.app.di

import android.content.Context
import com.fcmchat.fcmchat.fcm.db.AppDatabase
import com.fcmchat.fcmchat.fcm.db.repo.*
import com.fcmchat.fcmchat.fcm.repo.IFcmRepo
import com.fcmchat.fcmchat.fcm.services.DbUpdateService
import com.fcmchat.fcmchat.fcm.services.InitDbService
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
    fun getDatabase(): AppDatabase
    fun getFcmRepo(): IFcmRepo
    fun getChainRepo(): IChainsRepo
    fun getTransactionsRepo(): ITransactionRepo
    fun getUsersRepo(): IUsersRepo

    fun inject(userRepo: UsersRepo)
    fun inject(initDbService: InitDbService)
    fun inject(dataDbService: DbUpdateService)
    fun inject(transactionRepo: TransactionRepo)

}