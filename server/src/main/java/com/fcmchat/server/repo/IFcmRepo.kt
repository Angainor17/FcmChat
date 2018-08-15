package com.fcmchat.server.fcm.repo

import com.fcmchat.server.db.entity.TransactionEntity
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * Created by Voronin Igor on 07.08.2018.
 */
interface IFcmRepo {

    fun notifyAll(paramName: String, message: String, topicName: String): Completable
    fun sendTo(key: String, paramName: String, message: String): Completable

    fun subscribeToTopic(topicName: String)

    fun getFcmKey(): String
    fun getUserName(): String
    fun getTransactions(): Flowable<ArrayList<TransactionEntity>>

}