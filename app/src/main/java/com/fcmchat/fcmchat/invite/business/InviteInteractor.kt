package com.fcmchat.fcmchat.invite.business

import com.fcmchat.fcmchat.app.App
import com.fcmchat.fcmchat.chains.interactor.Microchain
import com.fcmchat.fcmchat.debug
import com.fcmchat.fcmchat.fcm.db.entity.UserEntity
import com.fcmchat.fcmchat.fcm.db.repo.IChainsRepo
import com.fcmchat.fcmchat.fcm.eventBus.InviteRequestEvent
import com.fcmchat.fcmchat.fcm.models.InviteRequestParams
import com.fcmchat.fcmchat.fcm.repo.IFcmRepo
import com.google.gson.Gson
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

/**
 * Created by Voronin Igor on 31.07.2018.
 */
class InviteInteractor : IInviteInteractor {

    @Inject lateinit var fcmRepo: IFcmRepo
    @Inject lateinit var chainsRepo: IChainsRepo

    init {
        App.injector.inviteComponent.inject(this)
        debug(getFcmKey())
    }

    override fun sendInvitation(key: String, microchain: Microchain): Completable {
        return chainsRepo.getChainByKey(microchain.chainKey).map { chainEntity ->
            val inviteRequest = InviteRequestParams(getCurrentUserEntity(microchain), chainEntity)
            val message = Gson().toJson(inviteRequest)
            fcmRepo.sendTo(key, InviteRequestEvent().getKey(), message)
        }.toCompletable()
    }

    override fun getFcmKey() = fcmRepo.getFcmKey()

    override fun subscribeToTopic(topicName: String) = fcmRepo.subscribeToTopic(topicName)

    override fun getCurrentUser() = User(name = fcmRepo.getUserName(), key = fcmRepo.getFcmKey())

    override fun getChains(): Flowable<ArrayList<Microchain>> =
            chainsRepo.getAllChains().map { ArrayList(it.map { (Microchain(it)) }) }

    private fun getCurrentUserEntity(microchain: Microchain): UserEntity {
        val userEntity = UserEntity()
        userEntity.fcmKey = fcmRepo.getFcmKey()
        userEntity.chainKey = microchain.chainName
        userEntity.name = fcmRepo.getUserName()

        return userEntity
    }
}