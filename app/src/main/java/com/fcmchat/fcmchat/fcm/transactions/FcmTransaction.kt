package com.fcmchat.fcmchat.fcm.transactions

import com.fcmchat.fcmchat.fcm.eventBus.TransactionEvent
import com.fcmchat.fcmchat.transactions.data.TransactionEntity
import com.google.gson.Gson

/**
 * Created by Voronin Igor on 10.08.2018.
 */
abstract class FcmTransaction {

    val type: String = initType()

    abstract fun initType(): String

    abstract fun init(text: String)

    abstract fun createTransactionEntity(request: TransactionEvent, transactions: ArrayList<TransactionEntity>): TransactionEntity

    override fun toString(): String = Gson().toJson(this)

}
