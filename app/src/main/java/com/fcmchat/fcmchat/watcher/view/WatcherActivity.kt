package com.fcmchat.fcmchat.watcher.view

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import com.arellomobile.mvp.MvpAppCompatActivity
import com.fcmchat.fcmchat.R
import com.fcmchat.fcmchat.fcm.eventBus.InviteRequestEvent
import com.fcmchat.fcmchat.fcm.eventBus.InviteResponseEvent
import com.fcmchat.fcmchat.fcm.eventBus.TransactionEvent
import com.fcmchat.fcmchat.watcher.presenter.IWatcherPresenter
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import javax.inject.Inject

/**
 * Created by Voronin Igor on 09.08.2018.
 */
abstract class WatcherActivity : MvpAppCompatActivity() {

    @Inject lateinit var watcherPresenter: IWatcherPresenter

//    @Subscribe(threadMode = ThreadMode.BACKGROUND)
//    fun inviteRequest(request: TransactionEvent) {
//        watcherPresenter.transactionEvent(request)
//    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun inviteRequest(request: InviteRequestEvent) = showReqAlertDialog(request)

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun inviteResponse(response: InviteResponseEvent) = showInviteResponseAlertDialog(response)

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    override fun onDestroy() {
        watcherPresenter.onDestroy()
        super.onDestroy()
    }

    private fun showInviteResponseAlertDialog(response: InviteResponseEvent) {
        val view = createResponseInviteView(response)

        AlertDialog.Builder(this)
                .setView(view)
                .setPositiveButton("Accept") { _, _ -> newUserAdded(view, response) }
                .create()
                .show()
    }

    private fun createResponseInviteView(response: InviteResponseEvent): View {
        val view = LayoutInflater.from(this).inflate(R.layout.invite_response_alert_dialog, null, false)
        view.findViewById<TextView>(R.id.user_name).text = response.slaveUser.name
        view.findViewById<TextView>(R.id.chain_name).text = response.chainEntity.name

        return view
    }

    private fun newUserAdded(view: View, response: InviteResponseEvent) {
        val isCanSend = view.findViewById<CheckBox>(R.id.send_check_box).isChecked
        val isCanInvite = view.findViewById<CheckBox>(R.id.invite_check_box).isChecked

        watcherPresenter.newUserAddToChain(response, isCanSend, isCanInvite)
    }

    private fun acceptInvitation(request: InviteRequestEvent) {
        watcherPresenter.acceptInvitation(request)
    }

    private fun showReqAlertDialog(request: InviteRequestEvent) =
            AlertDialog.Builder(this)
                    .setView(createReqInviteView(request))
                    .setPositiveButton("Accept") { _, _ -> acceptInvitation(request) }
                    .setNegativeButton("Cancel") { _, _ -> }
                    .create()
                    .show()

    private fun createReqInviteView(request: InviteRequestEvent): View {
        val view = LayoutInflater.from(this).inflate(R.layout.invite_alert_dialog, null, false)
        val chainNameTv = view.findViewById<TextView>(R.id.chain_name)!!
        val userNameTv = view.findViewById<TextView>(R.id.user_name)!!

        chainNameTv.text = request.chain.name
        userNameTv.text = request.masterUser.name

        return view
    }
}