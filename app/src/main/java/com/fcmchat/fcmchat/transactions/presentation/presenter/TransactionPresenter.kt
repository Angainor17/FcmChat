package com.fcmchat.fcmchat.transactions.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.fcmchat.fcmchat.app.App
import com.fcmchat.fcmchat.chains.interactor.Microchain
import com.fcmchat.fcmchat.transactions.interactor.ITransactionInteractor
import com.fcmchat.fcmchat.transactions.interactor.TransactionItem
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Voronin Igor on 06.08.2018.
 */
@InjectViewState
class TransactionPresenter : AbstractTransactionPresenter() {

    @Inject lateinit var interactor: ITransactionInteractor
    private val chainsCompositeDisposable = CompositeDisposable()
    private val transactionsCompositeDisposable = CompositeDisposable()
    private val transactions = ArrayList<TransactionItem>()
    private val chains = ArrayList<Microchain>()

    init {
        App.injector.transactionComponent.inject(this)
    }

    override fun addTransactionBtnClick() {
        viewState.showTransactionDialog()
    }

    override fun viewCreated(chainName: String) {
        viewState.setTransactionsList(transactions)
        chainChange(chainName)
        initChainsListener()
    }

    private fun initChainsListener() {
        chainsCompositeDisposable.clear()
        chainsCompositeDisposable.add(interactor.getChains()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ setChains(it) }) { _ -> setChains(ArrayList()) }
        )
    }

    override fun chainChange(chainName: String) {
        if (chainName.isEmpty()) return

        transactionsCompositeDisposable.clear()
        transactionsCompositeDisposable.add(interactor.getTransactions(getChainByName(chainName))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ list -> setTransactions(list) })
                {e-> setTransactions(ArrayList()) })
    }

    private fun getChainByName(chainName: String) = chains.first { chainName == it.chainName }

    private fun setChains(newList: ArrayList<Microchain>) {
        chains.clear()
        chains.addAll(newList)
        viewState.setChainsList(ArrayList(chains.map { it.chainName }))
//        chainChange()
    }

    private fun setTransactions(newList: ArrayList<TransactionItem>) {
        transactions.clear()
        transactions.addAll(newList)
        viewState.setTransactionsList(transactions)
    }

    override fun onDestroy() {
        transactionsCompositeDisposable.clear()
        chainsCompositeDisposable.clear()
        super.onDestroy()
    }
}