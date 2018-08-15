package com.fcmchat.server.db.repo

import com.fcmchat.server.db.AppDatabase
import com.fcmchat.server.db.entity.TransactionEntity
import com.fcmchat.server.di.ServerInjector
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Created by Voronin Igor on 06.08.2018.
 */
class TransactionRepo : ITransactionRepo {

    @Inject lateinit var db: AppDatabase

    init {
        ServerInjector.mainComponent.inject(this)
    }

    override fun addTransaction(newEntity: TransactionEntity) = db.transactionDao().insert(newEntity)

    override fun getTransaction(): Flowable<List<TransactionEntity>> = db.transactionDao().getAll()
}