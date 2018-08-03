package com.fcmchat.fcmchat.invite.business

import io.reactivex.Completable

/**
 * Created by Voronin Igor on 31.07.2018.
 */
interface IInviteActivityInteractor {

    fun sendMessageAll(message: String): Completable
    fun sendMessageTo(key: String, message: String): Completable
    fun saveFcmKey(key: String)


}