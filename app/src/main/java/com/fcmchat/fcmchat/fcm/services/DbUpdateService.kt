package com.fcmchat.fcmchat.fcm.services

import android.content.Intent
import com.fcmchat.fcmchat.app.App
import com.fcmchat.fcmchat.fcm.db.repo.ITransactionRepo
import com.fcmchat.fcmchat.fcm.transactions.DataTransaction
import com.fcmchat.fcmchat.watcher.interactor.transactions.TransactionValidator
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


class DbUpdateService : DbService("DbUpdateService") {

    @Inject lateinit var transactRepo: ITransactionRepo

    private val compositeDisposable = CompositeDisposable()

    override fun onHandleIntent(intent: Intent?) {
        App.injector.appComponent.inject(this)
        super.onHandleIntent(intent)
        val dataTransaction = transactionEvent.transaction as DataTransaction

        newUserTransaction(dataTransaction)
    }

    private fun newUserTransaction(dataTransaction: DataTransaction) {
        val transactionValidator = TransactionValidator()
        compositeDisposable.add(transactRepo.getTransactions().subscribe(
                { transactions ->
                    if (transactionValidator.isValid(dataTransaction.transaction, transactions)) {
                        transactRepo.addTransaction(dataTransaction.transaction)
                    }
                }) { }
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    private fun addTransaction() {

    }
}
