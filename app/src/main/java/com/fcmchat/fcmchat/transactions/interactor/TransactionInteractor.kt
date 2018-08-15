package com.fcmchat.fcmchat.transactions.interactor

import com.fcmchat.fcmchat.app.App
import com.fcmchat.fcmchat.chains.interactor.Microchain
import com.fcmchat.server.fcm.repo.IFcmRepo
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Voronin Igor on 06.08.2018.
 */
class TransactionInteractor : ITransactionInteractor {

    @Inject lateinit var fcmRepo: IFcmRepo

    init {
        App.injector.transactionComponent.inject(this)
    }

    override fun getTransactions(chain: Microchain): Flowable<ArrayList<TransactionItem>> =
            fcmRepo.getTransactions().map { ArrayList(it) }
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .observeOn(Schedulers.io())
                    .map { ArrayList(it.map { TransactionItem(it!!) }) }
                    .map { ArrayList(it.filter { it.chainName == chain.chainName }) }

    override fun getChains(): Flowable<ArrayList<Microchain>> = Flowable.just(ArrayList())//fixme
//            chainsRepo.getAllChains().map { ArrayList(it.map { (Microchain(it)) }) }
}