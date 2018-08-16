package com.fcmchat.fcmchat.transactions.presentation.view

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.fcmchat.fcmchat.transactions.interactor.TransactionItem

/**
 * Created by Voronin Igor on 06.08.2018.
 */

interface ITransactionView : MvpView {

    @StateStrategyType(SkipStrategy::class)
    fun showTransactionDialog()

    @StateStrategyType(SkipStrategy::class)
    fun setTransactionsList(list: ArrayList<TransactionItem>)

    @StateStrategyType(SkipStrategy::class)
    fun setChainsList(list: ArrayList<String>)

}