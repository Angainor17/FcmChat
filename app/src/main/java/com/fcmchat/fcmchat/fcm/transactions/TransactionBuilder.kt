package com.fcmchat.fcmchat.fcm.transactions

/**
 * Created by Voronin Igor on 10.08.2018.
 */
class TransactionBuilder {
    companion object {
        fun transactions() = arrayListOf(
                InitTransaction(),
                DataTransaction(),
                DefaultTransaction()
        )
    }
}