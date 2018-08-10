package com.fcmchat.fcmchat.transactions.interactor

import com.fcmchat.fcmchat.app.App
import com.fcmchat.fcmchat.chains.data.IChainsRepo
import com.fcmchat.fcmchat.chains.interactor.Microchain
import com.fcmchat.fcmchat.transactions.data.ITransactionRepo
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Voronin Igor on 06.08.2018.
 */
class TransactionInteractor : ITransactionInteractor {

    @Inject lateinit var repo: ITransactionRepo
    @Inject lateinit var chainsRepo: IChainsRepo

    init {
        App.injector.transactionComponent.inject(this)
    }

    override fun getTransactions(chain: Microchain): Flowable<ArrayList<TransactionItem>> =
            repo.getTransaction().map { ArrayList(it) }
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .observeOn(Schedulers.io())
                    .map { ArrayList(it.map { TransactionItem(it!!) }) }

    override fun getChains(): Flowable<ArrayList<Microchain>> =
            chainsRepo.getAllChains().map { ArrayList(it.map { (Microchain(it)) }) }
}