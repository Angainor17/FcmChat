package com.fcmchat.server.fcm.transactions

import com.fcmchat.server.db.entity.TransactionEntity
import com.fcmchat.server.fcm.eventBus.TransactionEvent
import com.google.gson.Gson

/**
 * Created by Voronin Igor on 10.08.2018.
 */
class MessageTransaction : FcmTransaction() {

    var message = ""

    override fun initType(): String = "message"

    override fun init(text: String) {
        val newUserTransaction = Gson().fromJson(text, MessageTransaction::class.java)

        this.message = newUserTransaction.message
    }

    override fun createTransactionEntity(request: TransactionEvent, transactions: ArrayList<TransactionEntity>): TransactionEntity {
        val transactionEntity = TransactionEntity()

        return transactionEntity
    }
}