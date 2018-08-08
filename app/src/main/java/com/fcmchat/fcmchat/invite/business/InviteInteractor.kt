package com.fcmchat.fcmchat.invite.business

import com.fcmchat.fcmchat.app.App
import com.fcmchat.fcmchat.chains.data.IChainsRepo
import com.fcmchat.fcmchat.chains.interactor.Microchain
import com.fcmchat.fcmchat.fcm.repo.IFcmRepo
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
    }

    override fun sendMessageAll(message: String, topicName: String) = fcmRepo.notifyAll(message, topicName)

    override fun sendMessageTo(key: String, message: String) = fcmRepo.sendTo(key, message)

    override fun getFcmKey() = fcmRepo.getFcmKey()

    override fun subscribeToTopic(topicName: String) = fcmRepo.subscribeToTopic(topicName)

    override fun getCurrentUser() = User(name = fcmRepo.getUserName(), key = fcmRepo.getFcmKey())

    override fun getChains(): Flowable<ArrayList<Microchain>> =
            chainsRepo.getAllChains().map { ArrayList(it.map { (Microchain(it)) }) }

}