package com.fcmchat.fcmchat.fcm.db.repo

import com.fcmchat.fcmchat.app.App
import com.fcmchat.fcmchat.fcm.db.AppDatabase
import com.fcmchat.fcmchat.fcm.db.entity.TransactionEntity
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Created by Voronin Igor on 06.08.2018.
 */
class TransactionRepo : ITransactionRepo {

    @Inject lateinit var db: AppDatabase

    init {
        App.injector.appComponent.inject(this)
    }

    override fun addTransaction(newEntity: TransactionEntity) =
            Completable.fromCallable { db.transactionDao().insert(newEntity) }!!

    override fun getTransactions(chainKey: String): Flowable<ArrayList<TransactionEntity>> =
            db.transactionDao().getAllBuChainKey(chainKey).map { ArrayList(it) }

    override fun getTransactions() = db.transactionDao().getAll().map { ArrayList(it) }!!
}