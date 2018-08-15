package com.fcmchat.server.fcm.transactions

import com.fcmchat.server.db.entity.TransactionEntity
import com.fcmchat.server.fcm.eventBus.TransactionEvent
import com.google.gson.Gson

/**
 * Created by Voronin Igor on 10.08.2018.
 */
class NewUserTransaction : FcmTransaction() {

    override fun initType(): String = "news_user"

    override fun init(text: String) {
        val newUserTransaction = Gson().fromJson(text, NewUserTransaction::class.java)
    }

    override fun createTransactionEntity(request: TransactionEvent, transactions: ArrayList<TransactionEntity>): TransactionEntity {//fixme
        val newUserTransaction = request.transaction as NewUserTransaction
        val transactionEntity = TransactionEntity()

        return transactionEntity
    }

    private fun caclHash(newUserTransaction: NewUserTransaction, transactions: ArrayList<TransactionEntity>): String {
//        if(transactions.isEmpty()){
//            return newUserTransaction.hashCode()
//        }

        return "lol"
    }
}