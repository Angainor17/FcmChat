package com.fcmchat.fcmchat.chains.interactor

import com.fcmchat.fcmchat.fcm.db.entity.ChainEntity

/**
 * Created by Voronin Igor on 06.08.2018.
 */
data class Microchain(
        val chainName: String = "",
        val chainKey: String = ""
) {

    constructor(chainEntity: ChainEntity) : this(chainEntity.name)
}