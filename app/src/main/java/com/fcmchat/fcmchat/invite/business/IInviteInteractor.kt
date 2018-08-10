package com.fcmchat.fcmchat.invite.business

import com.fcmchat.fcmchat.chains.interactor.Microchain
import io.reactivex.Completable
import io.reactivex.Flowable

/**
 * Created by Voronin Igor on 31.07.2018.
 */
interface IInviteInteractor {

    fun sendInvitation(key: String, microchain: Microchain): Completable
    fun getFcmKey(): String
    fun subscribeToTopic(topicName: String)
    fun getCurrentUser(): User

    fun getChains(): Flowable<ArrayList<Microchain>>

}