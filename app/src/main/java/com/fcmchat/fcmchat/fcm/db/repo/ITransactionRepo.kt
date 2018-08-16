package com.fcmchat.fcmchat.fcm.db.repo

import com.fcmchat.fcmchat.fcm.db.entity.TransactionEntity
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * Created by Voronin Igor on 06.08.2018.
 */
interface ITransactionRepo {
    fun getTransactions(): Flowable<ArrayList<TransactionEntity>>
    fun getTransactions(chainKey: String): Flowable<ArrayList<TransactionEntity>>
    fun addTransaction(newEntity: TransactionEntity): Completable
}