package com.fcmchat.fcmchat.chains.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.fcmchat.fcmchat.R
import com.fcmchat.fcmchat.app.App
import com.fcmchat.fcmchat.chains.presentation.presenter.AbstractChainsPresenter
import com.fcmchat.fcmchat.chains.presentation.presenter.ChainsPresenter
import javax.inject.Inject

/**
 * Created by Voronin Igor on 06.08.2018.
 */
class ChainsFragment : MvpAppCompatFragment(), IChainsView {

    @InjectPresenter lateinit var presenter: AbstractChainsPresenter
    @ProvidePresenter fun createPresenter(): AbstractChainsPresenter = ChainsPresenter()
    @Inject lateinit var daggerPresenter: AbstractChainsPresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        ButterKnife.bind(view)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.injector.chainsComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            layoutInflater.inflate(R.layout.chains_fragment_layout, container, false)

}