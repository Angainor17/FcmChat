package com.fcmchat.fcmchat.invite.business

import io.reactivex.Completable

/**
 * Created by Voronin Igor on 31.07.2018.
 */
interface IInviteInteractor {

    fun sendMessageAll(message: String, topicName: String): Completable
    fun sendMessageTo(key: String, message: String): Completable
    fun getFcmKey(): String
    fun subscribeToTopic(topicName: String)
    fun getCurrentUser(): User

}