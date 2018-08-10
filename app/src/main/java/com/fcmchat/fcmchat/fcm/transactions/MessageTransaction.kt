package com.fcmchat.fcmchat.fcm.transactions

import com.fcmchat.fcmchat.fcm.eventBus.TransactionEvent
import com.fcmchat.fcmchat.invite.data.UserEntity
import com.fcmchat.fcmchat.transactions.data.TransactionEntity
import com.google.gson.Gson

/**
 * Created by Voronin Igor on 10.08.2018.
 */
class MessageTransaction : FcmTransaction() {

    var parentUserEntity: UserEntity? = null
    var message = ""

    override fun initType(): String = "message"

    override fun init(text: String) {
        val newUserTransaction = Gson().fromJson(text, MessageTransaction::class.java)

        this.message = newUserTransaction.message
        this.parentUserEntity = newUserTransaction.parentUserEntity
    }

    override fun createTransactionEntity(request: TransactionEvent, transactions: ArrayList<TransactionEntity>): TransactionEntity {
        val transactionEntity = TransactionEntity()

        return transactionEntity
    }
}