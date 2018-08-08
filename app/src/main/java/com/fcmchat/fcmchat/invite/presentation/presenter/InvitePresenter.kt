package com.fcmchat.fcmchat.invite.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.fcmchat.fcmchat.app.App
import com.fcmchat.fcmchat.fcm.models.InviteRequest
import com.fcmchat.fcmchat.invite.business.IInviteInteractor
import com.fcmchat.fcmchat.invite.presentation.view.IInviteActivityView
import com.google.gson.Gson
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

    init {
        App.injector.inviteComponent.inject(this)
//        EventBus.getDefault().register(this)
        initFcm()
    }

    override fun onDestroy() {
//        EventBus.getDefault().unregister(this)
        super.onDestroy()
    }

    fun showQrCodeBtnClick() = viewState.showQrCode(firebaseToken)

    fun sendInvitationBtnClick(key: String) {
        if (!key.isEmpty()) {
            compositeDisposable.add(interactor.sendMessageTo(key, Gson().toJson(createInviteMessage()))
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe()
            )
        }
    }

    fun viewCreated() {
        viewState.setDeviceId(interactor.getCurrentUser().name)
    }

    private fun initFcm() {
        firebaseToken = interactor.getFcmKey()
    }

    private fun createInviteMessage(): InviteRequest {
        val user = interactor.getCurrentUser()
        return InviteRequest(user.name, user.key)
    }
}