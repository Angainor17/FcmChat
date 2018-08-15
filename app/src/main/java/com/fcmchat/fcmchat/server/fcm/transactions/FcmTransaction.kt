package com.fcmchat.server.fcm.transactions

import com.fcmchat.server.db.entity.TransactionEntity
import com.fcmchat.server.fcm.eventBus.TransactionEvent
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
