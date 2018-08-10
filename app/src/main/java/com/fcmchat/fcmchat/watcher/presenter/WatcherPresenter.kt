package com.fcmchat.fcmchat.watcher.presenter

import com.fcmchat.fcmchat.app.App
import com.fcmchat.fcmchat.fcm.eventBus.InviteRequestEvent
import com.fcmchat.fcmchat.fcm.eventBus.InviteResponseEvent
import com.fcmchat.fcmchat.fcm.eventBus.TransactionEvent
import com.fcmchat.fcmchat.watcher.interactor.IWatcherInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by Voronin Igor on 10.08.2018.
 */
class WatcherPresenter : IWatcherPresenter {

    @Inject lateinit var interactor: IWatcherInteractor

    private val compositeDisposable = CompositeDisposable()

    init {
        App.injector.navComponent.inject(this)
    }

    override fun transactionEvent(request: TransactionEvent) = interactor.transactionEvent(request)

    override fun acceptInvitation(request: InviteRequestEvent) {
        compositeDisposable.add(interactor.acceptInvitation(request)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ }) { _ -> })
    }

    override fun newUserAddToChain(response: InviteResponseEvent, sendFlag: Boolean, inviteFlag: Boolean) {
        compositeDisposable.add(interactor.newUserAddToChain(response, sendFlag, inviteFlag)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ }, { _ -> }))
    }

    override fun onDestroy() = compositeDisposable.clear()
}