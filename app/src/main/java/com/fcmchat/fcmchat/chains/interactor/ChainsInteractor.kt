package com.fcmchat.fcmchat.chains.interactor

import com.fcmchat.fcmchat.app.App
import com.fcmchat.fcmchat.chains.data.ChainEntity
import com.fcmchat.fcmchat.chains.data.IChainsRepo
import com.fcmchat.fcmchat.db.AppDatabase
import com.fcmchat.fcmchat.fcm.repo.IFcmRepo
import com.fcmchat.fcmchat.invite.data.UserEntity
import com.fcmchat.fcmchat.transactions.data.ITransactionRepo
import com.fcmchat.fcmchat.transactions.data.TransactionEntity
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Voronin Igor on 06.08.2018.
 */
class ChainsInteractor : IChainsInteractor {

    @Inject lateinit var repo: IChainsRepo
    @Inject lateinit var fcmRepo: IFcmRepo
    @Inject lateinit var db: AppDatabase
    @Inject lateinit var transactRepo: ITransactionRepo

    init {
        App.injector.chainsComponent.inject(this)
    }

    override fun getAllChains(): Single<ArrayList<Microchain>> =
            repo.getAllChains().subscribeOn(Schedulers.io()).map { it ->
                val list = ArrayList<Microchain>()
                it.sortBy { -it.id }
                it.forEach { list.add(Microchain(it.name)) }
                list
            }.observeOn(AndroidSchedulers.mainThread()).firstOrError()

    override fun addNewChain(chain: Microchain) {
        val chainEntity = ChainEntity()
        chainEntity.name = chain.chainName

        repo.addChain(chainEntity)

        val userEntity = addFirstUser(chainEntity)
        addFirstTransaction(userEntity, chainEntity)
        subscribeToTopic(chain.chainName)
    }

    private fun addFirstTransaction(userEntity: UserEntity, chain: ChainEntity) {
        val initTransaction = TransactionEntity(userEntity, chain)

        transactRepo.addTransaction(initTransaction)
    }

    private fun addFirstUser(chain: ChainEntity): UserEntity {
        val userEntity = getUserEntity(chain)
        db.userDao().insert(userEntity)
        return userEntity
    }

    private fun getUserEntity(chain: ChainEntity): UserEntity {
        val userEntity = UserEntity()
        userEntity.name = fcmRepo.getUserName()
        userEntity.key = fcmRepo.getFcmKey()
        userEntity.policy = UserEntity.SEND_AND_INVITE_POLICY
        userEntity.chainId = chain.id
        userEntity.chainName = chain.name
        return userEntity
    }

    override fun subscribeToTopic(chainName: String) = fcmRepo.subscribeToTopic(chainName)
}