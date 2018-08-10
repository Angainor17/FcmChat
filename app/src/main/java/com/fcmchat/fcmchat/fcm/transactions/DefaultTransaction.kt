package com.fcmchat.fcmchat.fcm.transactions

import com.fcmchat.fcmchat.fcm.eventBus.TransactionEvent
import com.fcmchat.fcmchat.transactions.data.TransactionEntity

/**
 * Created by Voronin Igor on 10.08.2018.
 */
class DefaultTransaction : FcmTransaction() {

    override fun initType(): String = ""

    override fun init(text: String) {}

    override fun createTransactionEntity(request: TransactionEvent, transactions: ArrayList<TransactionEntity>): TransactionEntity {
        return TransactionEntity()
    }
}
