package com.fcmchat.fcmchat.transactions.di

import com.fcmchat.fcmchat.transactions.data.ITransactionRepo
import com.fcmchat.fcmchat.transactions.data.TransactionRepo
import com.fcmchat.fcmchat.transactions.interactor.ITransactionInteractor
import com.fcmchat.fcmchat.transactions.interactor.TransactionInteractor
import com.fcmchat.fcmchat.transactions.presentation.presenter.AbstractTransactionPresenter
import com.fcmchat.fcmchat.transactions.presentation.presenter.TransactionPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by Voronin Igor on 06.08.2018.
 */
@Module
class TransactionModule {

    @Provides fun createPresenter(): AbstractTransactionPresenter = TransactionPresenter()

    @Provides fun createRepo(): ITransactionRepo = TransactionRepo()

    @Provides fun createInteractor(): ITransactionInteractor = TransactionInteractor()

}