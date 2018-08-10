package com.fcmchat.fcmchat.transactions.interactor

import com.fcmchat.fcmchat.chains.interactor.Microchain
import io.reactivex.Flowable

/**
 * Created by Voronin Igor on 06.08.2018.
 */
interface ITransactionInteractor {

    fun getTransactions(chain: Microchain): Flowable<ArrayList<TransactionItem>>
    fun getChains(): Flowable<ArrayList<Microchain>>

}