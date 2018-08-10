package com.fcmchat.fcmchat.fcm.transactions

import com.fcmchat.fcmchat.fcm.eventBus.TransactionEvent
import com.fcmchat.fcmchat.invite.data.UserEntity
import com.fcmchat.fcmchat.transactions.data.TransactionEntity
import com.google.gson.Gson

/**
 * Created by Voronin Igor on 10.08.2018.
 */
class NewUserTransaction(var newUserEntity: UserEntity? = null) : FcmTransaction() {

    var parentUserEntity: UserEntity? = null

    override fun initType(): String = "news_user"

    override fun init(text: String) {
        val newUserTransaction = Gson().fromJson(text, NewUserTransaction::class.java)

        this.newUserEntity = newUserTransaction.newUserEntity
        this.parentUserEntity = newUserTransaction.parentUserEntity
    }

    override fun createTransactionEntity(request: TransactionEvent, transactions: ArrayList<TransactionEntity>): TransactionEntity {//fixme
        val newUserTransaction = request.transaction as NewUserTransaction
        val transactionEntity = TransactionEntity(newUserTransaction, caclHash(newUserTransaction, transactions))

        return transactionEntity
    }

    private fun caclHash(newUserTransaction: NewUserTransaction, transactions: ArrayList<TransactionEntity>): String {
//        if(transactions.isEmpty()){
//            return newUserTransaction.hashCode()
//        }

        return "lol"
    }
}