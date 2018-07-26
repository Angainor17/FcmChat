package com.fcmchat.fcmchat.mainActivity

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.fcmchat.fcmchat.debug
import com.fcmchat.fcmchat.eventBus.NewMessageEvent
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode


/**
 * Created by Voronin Igor on 16.07.2018.
 */
@InjectViewState
class MainActivityPresenter : MvpPresenter<IMainActivityView>() {

    private var firebaseToken = ""
    private val repository = MainActivityRepo()
    private val compositeDisposable = CompositeDisposable()

    init {
        initFirebaseToken()
        EventBus.getDefault().register(this)
    }

    private fun initFirebaseToken() {
        if(firebaseToken.isEmpty()){
            firebaseToken = FirebaseInstanceId.getInstance().token.toString()
            FirebaseMessaging.getInstance().subscribeToTopic("allDevices")
            debug(firebaseToken)
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: NewMessageEvent) {
        viewState.showText(event.remoteMessage["message"]!!)
    }

    fun sendMessageBtnClick(key: String, message: String) {
        if (!key.isEmpty()) {
            compositeDisposable.add(repository.sendMessageTo(key, message)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe()
            )
            return
        }

        compositeDisposable.add(repository.sendMessageAll(firebaseToken, message)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe()
        )
    }

    fun onStop() {
        Completable.create {
            FirebaseInstanceId.getInstance().deleteInstanceId()
            EventBus.getDefault().unregister(this)
        }.observeOn(Schedulers.io()).subscribeOn(Schedulers.io())
    }

    fun showQrCodeBtnClick() {
        viewState.showQrCode(firebaseToken)
    }
}