package com.fcmchat.fcmchat.chains.interactor

import com.fcmchat.fcmchat.app.App
import com.fcmchat.fcmchat.fcm.db.entity.ChainEntity
import com.fcmchat.fcmchat.fcm.db.entity.UserEntity
import com.fcmchat.fcmchat.fcm.db.repo.IChainsRepo
import com.fcmchat.fcmchat.fcm.db.repo.ITransactionRepo
import com.fcmchat.fcmchat.fcm.db.repo.IUsersRepo
import com.fcmchat.fcmchat.fcm.repo.IFcmRepo
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
    @Inject lateinit var transactRepo: ITransactionRepo
    @Inject lateinit var usersRepo: IUsersRepo

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
        usersRepo.addUsers(getUserEntity(chainEntity), chainEntity)

        subscribeToTopic(chain.chainName)
    }

    private fun getUserEntity(chain: ChainEntity): UserEntity {
        val userEntity = UserEntity()
        userEntity.name = fcmRepo.getUserName()
        userEntity.data = UserEntity.SEND_AND_INVITE_POLICY.toString()
        userEntity.chainKey = chain.key

        return userEntity
    }

    override fun subscribeToTopic(chainName: String) = fcmRepo.subscribeToTopic(chainName)
}