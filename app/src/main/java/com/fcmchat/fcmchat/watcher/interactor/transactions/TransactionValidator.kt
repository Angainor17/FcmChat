package com.fcmchat.fcmchat.watcher.interactor.transactions

import com.fcmchat.fcmchat.transactions.data.TransactionEntity

/**
 * Created by Voronin Igor on 10.08.2018.
 */
class TransactionValidator {

    fun isValid(newTransaction: TransactionEntity, transactionList: ArrayList<TransactionEntity>): Boolean {
        val isHashValid = isHashValid(newTransaction, transactionList)
        val isActionLegal = isActionLegal(newTransaction, transactionList)

        return isHashValid && isActionLegal
    }

    private fun isHashValid(newTransaction: TransactionEntity, transactionList: ArrayList<TransactionEntity>): Boolean {
        return true
    }


    private fun isActionLegal(newTransaction: TransactionEntity, transactionList: ArrayList<TransactionEntity>): Boolean {
        return true
    }
}