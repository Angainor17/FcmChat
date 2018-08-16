package com.fcmchat.fcmchat.fcm.transactions

import com.fcmchat.fcmchat.fcm.db.entity.TransactionEntity
import com.google.gson.Gson

/**
 * Created by Voronin Igor on 10.08.2018.
 */
class DataTransaction() : FcmTransaction() {

    var data = ""
    lateinit var transaction: TransactionEntity

    constructor(transactionEntity: TransactionEntity) : this() {
        transaction = transactionEntity
    }

    override fun initType(): String = "message"

    override fun init(text: String) {
        val newUserTransaction = Gson().fromJson(text, DataTransaction::class.java)

    }
}