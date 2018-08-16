package com.fcmchat.fcmchat.fcm.transactions

/**
 * Created by Voronin Igor on 10.08.2018.
 */
class DefaultTransaction : FcmTransaction() {

    override fun initType(): String = ""

    override fun init(text: String) {}
}
