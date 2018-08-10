package com.fcmchat.fcmchat.fcm.eventBus

import com.fcmchat.fcmchat.fcm.transactions.DefaultTransaction
import com.fcmchat.fcmchat.fcm.transactions.FcmTransaction
import com.fcmchat.fcmchat.fcm.transactions.TransactionBuilder
import com.google.gson.Gson

/**
 * Created by Voronin Igor on 08.08.2018.
 */
class TransactionEvent : Event() {

    lateinit var transaction: FcmTransaction

    override fun setMap(map: Map<String, String>) {
        val json = map[getKey()]
        val transactionAbstraction = Gson().fromJson(json, DefaultTransaction::class.java)!!

        transaction = TransactionBuilder.transactions().first { it.type == transactionAbstraction.type }
        transaction.init(json!!)
    }

    override fun getKey() = "transaction"
}