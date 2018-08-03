package com.fcmchat.fcmchat.invite.data

import io.reactivex.Completable

/**
 * Created by Voronin Igor on 31.07.2018.
 */
interface IInviteActivityRepo {

    fun sendMessageAll(message: String): Completable
    fun sendMessageTo(key: String, message: String): Completable
    fun saveFcmKey(key: String)

}