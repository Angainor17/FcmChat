package com.fcmchat.fcmchat.chains.interactor

import com.fcmchat.fcmchat.app.App
import com.fcmchat.server.fcm.repo.IFcmRepo
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Voronin Igor on 06.08.2018.
 */
class ChainsInteractor : IChainsInteractor {

    @Inject lateinit var fcmRepo: IFcmRepo

    init {
        App.injector.chainsComponent.inject(this)
    }

    override fun getAllChains(): Single<ArrayList<Microchain>> =
            fcmRepo.getTransactions().subscribeOn(Schedulers.io()).map { it ->
                val list = ArrayList<Microchain>()
                it.sortBy { -it.id }
//                it.forEach { list.add(Microchain(it.data)) }//fixme
                list
            }.observeOn(AndroidSchedulers.mainThread()).firstOrError()

    override fun addNewChain(chain: Microchain) {

    }

    override fun subscribeToTopic(chainName: String) = fcmRepo.subscribeToTopic(chainName)
}