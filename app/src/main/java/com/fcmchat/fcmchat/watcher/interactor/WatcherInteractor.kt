package com.fcmchat.fcmchat.watcher.interactor

import com.fcmchat.fcmchat.app.App
import com.fcmchat.fcmchat.fcm.db.entity.ChainEntity
import com.fcmchat.fcmchat.fcm.db.entity.TransactionEntity
import com.fcmchat.fcmchat.fcm.db.entity.UserEntity
import com.fcmchat.fcmchat.fcm.db.repo.IChainsRepo
import com.fcmchat.fcmchat.fcm.db.repo.ITransactionRepo
import com.fcmchat.fcmchat.fcm.db.repo.IUsersRepo
import com.fcmchat.fcmchat.fcm.eventBus.InviteRequestEvent
import com.fcmchat.fcmchat.fcm.eventBus.InviteResponseEvent
import com.fcmchat.fcmchat.fcm.eventBus.TransactionEvent
import com.fcmchat.fcmchat.fcm.models.InviteResponse
import com.fcmchat.fcmchat.fcm.repo.IFcmRepo
import com.fcmchat.fcmchat.fcm.transactions.DataTransaction
import com.fcmchat.fcmchat.fcm.transactions.InitTransaction
import com.fcmchat.fcmchat.watcher.interactor.transactions.TransactionValidator
import com.google.gson.Gson
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.functions.Function3
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

/**
 * Created by Voronin Igor on 09.08.2018.
 */
class WatcherInteractor : IWatcherInteractor {

    @Inject lateinit var fcmRepo: IFcmRepo
    @Inject lateinit var transactRepo: ITransactionRepo
    @Inject lateinit var userRepo: IUsersRepo
    @Inject lateinit var chainRepo: IChainsRepo

    private val transactionValidator = TransactionValidator()

    init {
        App.injector.navComponent.inject(this)
    }

    override fun transactionEvent(request: TransactionEvent) {
        when (request.transaction.type) {
            InitTransaction().type -> newUserTransaction(request)
            DataTransaction().type -> newMessageTransaction(request)
        }
    }

    private fun newMessageTransaction(request: TransactionEvent) {
        TODO("new message")
    }

    private fun newUserTransaction(request: TransactionEvent) {
        val newUserTransaction = request.transaction as InitTransaction
        transactRepo.getTransactions().map { ArrayList(it) }.subscribe(
                { transactions ->
                    val transactionEntity = TransactionEntity()
                    transactionEntity.type = TransactionEntity.NEW_USER_TYPE
                    transactionEntity.prevHash = transactions.last().hash
                    transactionEntity.chainKey = newUserTransaction.chain?.key ?: ""
                    transactionEntity.data = ""

                    if (transactionValidator.isValid(transactionEntity, transactions)) {
                        transactRepo.addTransaction(transactionEntity)
                    }
                }) { }
    }

    override fun acceptInvitation(request: InviteRequestEvent): Completable {
        val response = InviteResponse(request, fcmRepo.getFcmKey(), fcmRepo.getUserName(), result = UserEntity.OK_RESULT)

        return fcmRepo.sendTo(request.userKey, InviteResponseEvent().getKey(), Gson().toJson(response))
    }

    override fun newUserAddToChain(response: InviteResponseEvent, sendFlag: Boolean, inviteFlag: Boolean): Completable {
        if (response.result != UserEntity.OK_RESULT) return Completable.complete()

        return Single.zip(createNewUser(response, sendFlag, inviteFlag),
                chainRepo.getChainByKey(response.chainName),
                transactRepo.getTransactions(response.chainName).single(ArrayList()),
                Function3<UserEntity, ChainEntity, ArrayList<TransactionEntity>, UserEntity> { user, chain, transactions ->
                    user.chainKey = chain.key
                    notifyChain(user, transactions)
                    user
                })!!.toCompletable()
    }

    private fun notifyChain(newUser: UserEntity, transactions: ArrayList<TransactionEntity>) {
        val transaction = TransactionEntity()
        transaction.type = TransactionEntity.NEW_USER_TYPE
        transaction.chainKey = newUser.chainKey
        transaction.data = "Invite new Member"
        transaction.prevHash = transactions.last().hash

        transaction.initHash()

        fcmRepo.notifyAll(TransactionEvent().getKey(), Gson().toJson(transaction), newUser.name)
                .subscribe({}, { _ -> })
    }

    private fun createNewUser(response: InviteResponseEvent, sendFlag: Boolean, inviteFlag: Boolean): Single<UserEntity> {
        val userEntity = UserEntity()
        userEntity.name = response.userName
        userEntity.timestamp = Calendar.getInstance().timeInMillis
        userEntity.data = "" + UserEntity.getPolicy(sendFlag, inviteFlag)

        return Single.just(userEntity)
    }
}