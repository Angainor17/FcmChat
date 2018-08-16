package com.fcmchat.fcmchat.fcm.eventBus

import com.fcmchat.fcmchat.fcm.db.entity.ChainEntity
import com.fcmchat.fcmchat.fcm.db.entity.TransactionEntity
import com.fcmchat.fcmchat.fcm.db.entity.UserEntity
import com.google.gson.Gson

/**
 * Created by Voronin Igor on 08.08.2018.
 */
class InitDbEvent() : Event() {

    lateinit var users: ArrayList<UserEntity>
    lateinit var chainEntity: ChainEntity
    lateinit var transactions: ArrayList<TransactionEntity>

    override fun getKey() = "init_db"

    override fun setMap(map: Map<String, String>) {
        val params = Gson().fromJson(map[getKey()], InitDbEvent::class.java)!!

        this.users = params.users
        this.chainEntity = params.chainEntity
        this.transactions = params.transactions
    }

    constructor(users: ArrayList<UserEntity>, chainEntity: ChainEntity, transactions: ArrayList<TransactionEntity>) : this() {
        this.users = users
        this.chainEntity = chainEntity
        this.transactions = transactions
    }
}