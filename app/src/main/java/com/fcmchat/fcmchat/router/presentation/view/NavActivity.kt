package com.fcmchat.fcmchat.router.presentation.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.fcmchat.fcmchat.R
import com.fcmchat.fcmchat.app.App
import com.fcmchat.fcmchat.chains.presentation.view.ChainsFragment
import com.fcmchat.fcmchat.invite.presentation.view.InviteView
import com.fcmchat.fcmchat.router.presentation.presenter.INavPresenter
import com.fcmchat.fcmchat.router.presentation.presenter.NavPresenter
import com.fcmchat.fcmchat.transactions.presentation.view.TransactionsFragment
import kotlinx.android.synthetic.main.activity_navigation.*
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.SupportAppNavigator
import javax.inject.Inject

class NavActivity : MvpAppCompatActivity(), INavView {

    companion object {
        const val INVITE_SCREEN = "inviteScreen"
        const val TRANSACTIONS_SCREEN = "transactionsScreen"
        const val CHAINS_SCREEN = "chainsScreen"
    }

    @InjectPresenter lateinit var presenter: INavPresenter
    @Inject @Nullable lateinit var navHolder: NavigatorHolder
    @Inject @Nullable lateinit var router: Router
    @ProvidePresenter fun createPresenter(): INavPresenter = NavPresenter()

    private val inviteScreen = InviteView()
    private val transactionsScreen = TransactionsFragment()
    private val chainsScreen = ChainsFragment()

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_invite -> {
                title = "InviteRequest"
                presenter.screenBtnClick(INVITE_SCREEN)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_transaction -> {
                title = "Transactions"
                presenter.screenBtnClick(TRANSACTIONS_SCREEN)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_chains -> {
                title = "Chains"
                presenter.screenBtnClick(CHAINS_SCREEN)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.injector.navComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }

    override fun onResume() {
        super.onResume()
        navHolder.setNavigator(navigator)
        presenter.navigatorCreated()
    }

    override fun onPause() {
        navHolder.removeNavigator()
        super.onPause()
    }

    private var navigator: Navigator = object : SupportAppNavigator(this, R.id.container) {

        override fun createActivityIntent(context: Context?, screenKey: String?, data: Any?): Intent? = null

        override fun createFragment(screenKey: String, data: Any): Fragment? =
                when (screenKey) {
                    INVITE_SCREEN -> inviteScreen
                    TRANSACTIONS_SCREEN -> transactionsScreen
                    CHAINS_SCREEN -> chainsScreen
                    else -> null
                }

        override fun exit() {}
    }
}
