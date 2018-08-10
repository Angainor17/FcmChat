package com.fcmchat.fcmchat.router.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.fcmchat.fcmchat.app.App
import com.fcmchat.fcmchat.fcm.eventBus.InviteRequestEvent
import com.fcmchat.fcmchat.fcm.eventBus.InviteResponseEvent
import com.fcmchat.fcmchat.watcher.interactor.IWatcherInteractor
import com.fcmchat.fcmchat.router.presentation.view.NavActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Inject

/**
 * Created by Voronin Igor on 03.08.2018.
 */
@InjectViewState
class NavPresenter : INavPresenter() {

    @Inject lateinit var router: Router
    @Inject lateinit var navigatorHolder: NavigatorHolder
    @Inject lateinit var interactor: IWatcherInteractor

    private var isFirstNavigatorInit = true

    init {
        App.injector.navComponent.inject(this)
    }

    override fun navigatorCreated() {
        if (isFirstNavigatorInit) {
            isFirstNavigatorInit = false
            router.navigateTo(NavActivity.INVITE_SCREEN, 0)
        }
    }

    override fun screenBtnClick(key: String) = router.navigateTo(key, 0)

}