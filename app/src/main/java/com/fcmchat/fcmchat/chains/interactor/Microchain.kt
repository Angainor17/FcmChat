package com.fcmchat.fcmchat.chains.interactor

import com.fcmchat.fcmchat.chains.data.ChainEntity

/**
 * Created by Voronin Igor on 06.08.2018.
 */
data class Microchain(val chainName: String = "") {

    constructor(chainEntity: ChainEntity) : this(chainEntity.name)
}