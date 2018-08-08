package com.fcmchat.fcmchat.chains.interactor

import io.reactivex.Single

/**
 * Created by Voronin Igor on 06.08.2018.
 */
interface IChainsInteractor {

    fun addNewChain(chain: Microchain)
    fun getAllChains(): Single<ArrayList<Microchain>>
    fun subscribeToTopic(chainName: String)

}