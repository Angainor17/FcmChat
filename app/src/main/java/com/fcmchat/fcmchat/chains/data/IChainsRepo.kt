package com.fcmchat.fcmchat.chains.data

import io.reactivex.Single

/**
 * Created by Voronin Igor on 06.08.2018.
 */
interface IChainsRepo {

    fun addChain(newChain: ChainEntity)

    fun getAllChains(): Single<ArrayList<ChainEntity>>
}