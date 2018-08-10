package com.fcmchat.fcmchat.transactions.interactor

import com.fcmchat.fcmchat.transactions.data.TransactionEntity

/**
 * Created by Voronin Igor on 10.08.2018.
 */
class TransactionItem(
        transactionEntity: TransactionEntity,
        val type: String = transactionEntity.type,
        val message: String = transactionEntity.data,
        val chainName: String = transactionEntity.chainName
)