package com.fcmchat.fcmchat.chains.presentation.view

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.fcmchat.fcmchat.R
import com.fcmchat.fcmchat.app.App
import com.fcmchat.fcmchat.chains.interactor.Microchain
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

    @BindView(R.id.add_chain) lateinit var addChainBtn: Button
    @BindView(R.id.chains_list) lateinit var chainList: RecyclerView

    private val listAdapter: ChainsListAdapter = ChainsListAdapter(ArrayList())

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ButterKnife.bind(this, view)
        initChainListView()
    }

    override fun refreshList(newItems: ArrayList<Microchain>) {
        listAdapter.chainsList.clear()
        listAdapter.chainsList.addAll(newItems)
        listAdapter.notifyDataSetChanged()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        App.injector.chainsComponent.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            layoutInflater.inflate(R.layout.chains_fragment_layout, container, false)

    @OnClick(R.id.add_chain) fun addNewChainBtnClick() = presenter.addNewChainBtnClick()

    override fun showNewChainDialog() {
        val builder = AlertDialog.Builder(activity!!)
        val dialogView = createDialogView()

        builder.setView(dialogView)
        builder.setNegativeButton("Cancel") { _, _ -> }
        builder.setPositiveButton("Add") { _, _ -> alertDialogSuccess(dialogView) }

        val alertDialog = builder.create()
        alertDialog.show()
    }

    private fun alertDialogSuccess(dialogView: View) {
        val chainNameEditText = dialogView.findViewById<EditText>(R.id.chain_name_edit_text)
        presenter.alertDialogSuccess(chainNameEditText.text.toString())
    }

    private fun initChainListView() {
        chainList.adapter = listAdapter
        chainList.layoutManager = LinearLayoutManager(context)
        chainList.itemAnimator = DefaultItemAnimator()
    }

    private fun createDialogView(): View =
            LayoutInflater.from(context).inflate(R.layout.new_chain_dialog, null, false)
}