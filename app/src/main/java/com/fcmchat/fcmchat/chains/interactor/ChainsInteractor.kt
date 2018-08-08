package com.fcmchat.fcmchat.chains.interactor

import com.fcmchat.fcmchat.app.App
import com.fcmchat.fcmchat.chains.data.ChainEntity
import com.fcmchat.fcmchat.chains.data.IChainsRepo
import com.fcmchat.fcmchat.fcm.repo.IFcmRepo
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Voronin Igor on 06.08.2018.
 */
class ChainsInteractor : IChainsInteractor {

    @Inject lateinit var repo: IChainsRepo
    @Inject lateinit var fcmRepo: IFcmRepo

    init {
        App.injector.chainsComponent.inject(this)
    }

    override fun getAllChains(): Single<ArrayList<Microchain>> =
            repo.getAllChains().subscribeOn(Schedulers.io()).map { it ->
                val list = ArrayList<Microchain>()
                it.sortBy { -it.id }
                it.forEach { list.add(Microchain(it.name)) }
                list
            }.observeOn(AndroidSchedulers.mainThread()).firstOrError()

    override fun addNewChain(chain: Microchain) {
        val chainEntity = ChainEntity()
        chainEntity.name = chain.chainName

        repo.addChain(chainEntity)
        subscribeToTopic(chain.chainName)
    }

    override fun subscribeToTopic(chainName: String) = fcmRepo.subscribeToTopic(chainName)
}