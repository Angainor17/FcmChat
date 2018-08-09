package com.fcmchat.fcmchat.invite.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.fcmchat.fcmchat.app.App
import com.fcmchat.fcmchat.chains.interactor.Microchain
import com.fcmchat.fcmchat.invite.business.IInviteInteractor
import com.fcmchat.fcmchat.invite.presentation.view.IInviteActivityView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


/**
 * Created by Voronin Igor on 16.07.2018.
 */
@InjectViewState
class InvitePresenter : MvpPresenter<IInviteActivityView>() {

    @Inject lateinit var interactor: IInviteInteractor

    private var firebaseToken = ""
    private val compositeDisposable = CompositeDisposable()
    private val microchains = ArrayList<Microchain>()

    init {
        App.injector.inviteComponent.inject(this)
        initFcm()
        viewState.setChainsList(ArrayList(microchains.map { it.chainName }))
        initDbListeners()
    }

    fun showQrCodeBtnClick() = viewState.showQrCode(firebaseToken)

    fun sendInvitationBtnClick(key: String, chainName: String?) {
        if (key.isEmpty()) {
            viewState.showAlert("Enter sender Key")
            return
        }

        if (chainName == null || chainName.isEmpty()) {
            viewState.showAlert("Select Microchain")
            return
        }

        compositeDisposable.add(interactor.sendInvitation(key, getMicroChain(chainName)!!)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ }) { _ -> }
        )
    }

    fun viewCreated() {
        viewState.setDeviceId(interactor.getCurrentUser().name)
        initDbListeners()
    }

    private fun getMicroChain(microChainName: String): Microchain? {
        microchains.forEach { if (it.chainName == microChainName) return it }
        return null
    }

    private fun initFcm() {
        firebaseToken = interactor.getFcmKey()
    }


    private fun initDbListeners() {
        compositeDisposable.add(interactor.getChains()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        {
                            setChainsList(it)
                        },
                        {
                            setChainsList(ArrayList())
                        }))
    }

    private fun setChainsList(newList: ArrayList<Microchain>) {
        microchains.clear()
        microchains.addAll(newList)
        viewState.setChainsList(ArrayList(microchains.map { it.chainName }))
    }
}
