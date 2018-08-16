package com.fcmchat.fcmchat.fcm.transactions

import com.fcmchat.fcmchat.fcm.db.entity.ChainEntity
import com.fcmchat.fcmchat.fcm.db.entity.UserEntity
import com.google.gson.Gson

/**
 * Created by Voronin Igor on 10.08.2018.
 */
class InitTransaction : FcmTransaction() {

    var data = ""
    lateinit var chain: ChainEntity
    lateinit var chainMembers: UserEntity

    override fun initType(): String = "init_message"

    override fun init(text: String) {
        val newUserTransaction = Gson().fromJson(text, InitTransaction::class.java)

    }
}