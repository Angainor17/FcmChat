package com.fcmchat.fcmchat.mainActivity

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.fcmchat.fcmchat.debug
import com.google.firebase.iid.FirebaseInstanceId

/**
 * Created by Voronin Igor on 16.07.2018.
 */
@InjectViewState
class MainActivityPresenter : MvpPresenter<IMainActivityView>() {

    private var firebaseToken = ""
    private val repository = MainActivityRepo()

    override fun attachView(view: IMainActivityView?) {
        super.attachView(view)
        initFirebaseToken()
    }

    private fun initFirebaseToken() {
        firebaseToken = FirebaseInstanceId.getInstance().token.toString()
        debug(firebaseToken)
    }

    fun sendMessageBtnClick(key: String) {
        if (!key.isEmpty()) {
            repository.sendMessageAll(firebaseToken)
        }
    }

    fun onStop() {
        FirebaseInstanceId.getInstance().deleteInstanceId()
    }
}