package com.fcmchat.fcmchat.mainActivity.business

import io.reactivex.Completable

/**
 * Created by Voronin Igor on 31.07.2018.
 */
interface IMainActivityInteractor {

    fun sendMessageAll(message: String): Completable
    fun sendMessageTo(key: String, message: String): Completable
    fun saveFcmKey(key: String)


}