package com.fcmchat.fcmchat.app.di

import android.arch.persistence.room.Room
import android.content.Context
import com.fcmchat.fcmchat.fcm.db.AppDatabase
import com.fcmchat.fcmchat.fcm.db.repo.*
import com.fcmchat.fcmchat.fcm.repo.FcmRepo
import com.fcmchat.fcmchat.fcm.repo.IFcmRepo
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
    private var fcmRepo: IFcmRepo = FcmRepo(context)
    private var db: AppDatabase = Room.databaseBuilder(context, AppDatabase::class.java, "database")
            .allowMainThreadQueries()
            .build()

    @Provides fun getDb() = db
    @Provides fun context() = context
    @Provides fun getNavigatorHolder(): NavigatorHolder = cicerone.navigatorHolder
    @Provides fun getRouter(): Router = cicerone.router

    @Provides fun getFcmRepository(): IFcmRepo = fcmRepo
    @Provides fun createChainsRepo(): IChainsRepo = ChainsRepo()
    @Provides fun createUsersRepo(): IUsersRepo = UsersRepo()
    @Provides fun createTransactionRepo(): ITransactionRepo = TransactionRepo()

}