package com.fcmchat.fcmchat.fcm.repo

import io.reactivex.Completable

/**
 * Created by Voronin Igor on 07.08.2018.
 */
interface IFcmRepo {

    fun notifyAll(message: String, topicName: String): Completable
    fun sendTo(key: String, message: String): Completable

    fun subscribeToTopic(topicName: String)

    fun getFcmKey(): String
    fun getUserName(): String

}