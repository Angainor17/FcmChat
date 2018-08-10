package com.fcmchat.fcmchat.transactions.data

import com.fcmchat.fcmchat.app.App
import com.fcmchat.fcmchat.db.AppDatabase
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Created by Voronin Igor on 06.08.2018.
 */
class TransactionRepo : ITransactionRepo {

    @Inject lateinit var db: AppDatabase

    init {
        App.injector.transactionComponent.inject(this)
    }

    override fun addTransaction(newEntity: TransactionEntity) = db.transactionDao().insert(newEntity)

    override fun getTransaction(): Flowable<List<TransactionEntity>> = db.transactionDao().getAll()
}