package com.fcmchat.fcmchat.watcher.interactor

import com.fcmchat.fcmchat.app.App
import com.fcmchat.fcmchat.chains.data.ChainEntity
import com.fcmchat.fcmchat.db.AppDatabase
import com.fcmchat.fcmchat.fcm.eventBus.InviteRequestEvent
import com.fcmchat.fcmchat.fcm.eventBus.InviteResponseEvent
import com.fcmchat.fcmchat.fcm.eventBus.TransactionEvent
import com.fcmchat.fcmchat.fcm.models.InviteResponse
import com.fcmchat.fcmchat.fcm.repo.IFcmRepo
import com.fcmchat.fcmchat.fcm.transactions.MessageTransaction
import com.fcmchat.fcmchat.fcm.transactions.NewUserTransaction
import com.fcmchat.fcmchat.fcm.transactions.TransactionBuilder
import com.fcmchat.fcmchat.invite.data.UserEntity
import com.fcmchat.fcmchat.transactions.data.ITransactionRepo
import com.fcmchat.fcmchat.watcher.interactor.transactions.TransactionValidator
import com.google.gson.Gson
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.functions.BiFunction
import javax.inject.Inject

/**
 * Created by Voronin Igor on 09.08.2018.
 */
class WatcherInteractor : IWatcherInteractor {

    @Inject lateinit var fcmRepo: IFcmRepo
    @Inject lateinit var db: AppDatabase
    @Inject lateinit var transactRepo: ITransactionRepo

    private val transactionValidator = TransactionValidator()

    init {
        App.injector.navComponent.inject(this)
    }

    override fun transactionEvent(request: TransactionEvent) {
        when (request.transaction.type) {
            NewUserTransaction().type -> newUserTransaction(request)
            MessageTransaction().type -> newMessageTransaction(request)
        }
    }

    private fun newMessageTransaction(request: TransactionEvent) {
        TODO("new message")
    }

    private fun newUserTransaction(request: TransactionEvent) {
        val newUserTransaction = request.transaction as NewUserTransaction
        transactRepo.getTransaction().map { ArrayList(it) }.subscribe(
                { transactions ->
                    val transactionEntity = newUserTransaction.createTransactionEntity(request, transactions)
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

        return Single.zip(createNewUser(response, sendFlag, inviteFlag), getChainByName(response.chainName),
                BiFunction<UserEntity, ChainEntity, UserEntity> { user, chain ->
                    user.chainId = chain.id
                    user.chainName = chain.name
//                    addUserToDb(user)//fixme       ?
                    notifyChain(user)
                    user
                }).toCompletable()
    }

    private fun notifyChain(newUser: UserEntity) {
        val transaction = TransactionBuilder.userConvert(newUser)
        fcmRepo.notifyAll(TransactionEvent().getKey(), Gson().toJson(transaction), newUser.chainName)
                .subscribe({}, { _ -> })
    }

    private fun addUserToDb(user: UserEntity) = db.userDao().update(user)

    private fun createNewUser(response: InviteResponseEvent, sendFlag: Boolean, inviteFlag: Boolean): Single<UserEntity> {
        val userEntity = UserEntity()
        userEntity.key = response.userKey
        userEntity.name = response.userName
        userEntity.policy = UserEntity.getPolicy(sendFlag, inviteFlag)

        return Single.just(userEntity)
    }

    private fun getChainByName(chainName: String): Single<ChainEntity> = db.chainDao().getByName(chainName)

}