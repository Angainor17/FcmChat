package com.fcmchat.fcmchat.chains.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.fcmchat.fcmchat.app.App
import com.fcmchat.fcmchat.chains.interactor.IChainsInteractor
import com.fcmchat.fcmchat.chains.interactor.Microchain
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Voronin Igor on 06.08.2018.
 */
@InjectViewState
class ChainsPresenter : AbstractChainsPresenter() {

    @Inject lateinit var interactor: IChainsInteractor

    var chains: ArrayList<Microchain> = ArrayList()
    private val compositeDisposable = CompositeDisposable()

    init {
        App.injector.chainsComponent.inject(this)
        refreshList()
    }

    override fun addNewChainBtnClick() = viewState.showNewChainDialog()

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }

    override fun alertDialogSuccess(chainName: String) {
        val chain = Microchain(chainName)
        interactor.addNewChain(chain)
        refreshList()
    }

    private fun refreshList() {
        compositeDisposable.add(interactor.getAllChains()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({
                    setChainsItems(it)
                }, {
                    setChainsItems(ArrayList())
                }))
    }

    private fun setChainsItems(list: ArrayList<Microchain>) {
        chains.clear()
        chains.addAll(list)
        viewState.refreshList(chains)
    }
}