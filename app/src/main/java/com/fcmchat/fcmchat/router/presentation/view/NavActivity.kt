package com.fcmchat.fcmchat.router.presentation.view

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.fcmchat.fcmchat.R
import com.fcmchat.fcmchat.app.App
import com.fcmchat.fcmchat.invite.presentation.view.InviteView
import com.fcmchat.fcmchat.router.presentation.presenter.INavPresenter
import com.fcmchat.fcmchat.router.presentation.presenter.NavPresenter
import kotlinx.android.synthetic.main.activity_navigation.*
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.SupportFragmentNavigator
import javax.inject.Inject

class NavActivity : MvpAppCompatActivity(), INavView {

    companion object {
        const val INVITE_SCREEN = "inviteScreen"
        const val TRANSACTIONS_SCREEN = "transactionsScreen"
        const val CHAINS_SCREEN = "chainsScreen"
    }

    @InjectPresenter lateinit var presenter: NavPresenter
    @Inject lateinit var navHolder: NavigatorHolder
    @ProvidePresenter fun createPresenter(): INavPresenter = NavPresenter()

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_add -> {
                presenter.screenBtnClick(INVITE_SCREEN)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_transaction -> {
                presenter.screenBtnClick(TRANSACTIONS_SCREEN)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_chains -> {
                presenter.screenBtnClick(CHAINS_SCREEN)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.injector.navComponent.inject(this)
        setContentView(R.layout.activity_navigation)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    override fun onResume() {
        super.onResume()
        navHolder.setNavigator(navigator)
    }

    override fun onStop() {
        super.onStop()
        navHolder.removeNavigator()
    }

    private val navigator = object : SupportFragmentNavigator(supportFragmentManager, R.id.container) {
        override fun createFragment(screenKey: String, data: Any): Fragment =
                when (screenKey) {
                    INVITE_SCREEN -> InviteView()
                    TRANSACTIONS_SCREEN -> InviteView()//fixme
                    CHAINS_SCREEN -> InviteView()//fixme
                    else -> InviteView()
                }

        override fun showSystemMessage(message: String?) {}
        override fun exit() {}
    }
}
