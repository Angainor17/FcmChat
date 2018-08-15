package com.fcmchat.server.fcm.transactions

import com.fcmchat.server.db.entity.TransactionEntity
import com.fcmchat.server.fcm.eventBus.TransactionEvent

/**
 * Created by Voronin Igor on 10.08.2018.
 */
class DefaultTransaction : FcmTransaction() {

    override fun initType(): String = ""

    override fun init(text: String) {}
    override fun createTransactionEntity(request: TransactionEvent, transactions: ArrayList<TransactionEntity>): TransactionEntity {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return TransactionEntity()
    }
}
