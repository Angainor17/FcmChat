package com.fcmchat.fcmchat.transactions.di

import com.fcmchat.fcmchat.app.di.AppComponent
import com.fcmchat.fcmchat.fcm.db.repo.TransactionRepo
import com.fcmchat.fcmchat.transactions.interactor.TransactionInteractor
import com.fcmchat.fcmchat.transactions.presentation.presenter.TransactionPresenter
import com.fcmchat.fcmchat.transactions.presentation.view.TransactionsFragment
import dagger.Component

/**
 * Created by Voronin Igor on 06.08.2018.
 */
@Component(dependencies = [AppComponent::class], modules = [TransactionModule::class])
interface TransactionComponent {

    fun inject(repo: TransactionRepo)

    fun inject(interactor: TransactionInteractor)

    fun inject(presenter: TransactionPresenter)

    fun inject(view: TransactionsFragment)

}