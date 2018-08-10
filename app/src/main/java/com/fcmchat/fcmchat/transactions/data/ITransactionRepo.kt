package com.fcmchat.fcmchat.transactions.data

import io.reactivex.Flowable

/**
 * Created by Voronin Igor on 06.08.2018.
 */
interface ITransactionRepo {

    fun getTransaction(): Flowable<List<TransactionEntity>>
    fun addTransaction(newEntity: TransactionEntity)

}