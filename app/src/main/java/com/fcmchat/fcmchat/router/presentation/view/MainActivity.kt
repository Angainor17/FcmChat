package com.fcmchat.fcmchat.router.presentation.view

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.arellomobile.mvp.MvpAppCompatActivity
import com.fcmchat.fcmchat.R
import com.fcmchat.fcmchat.fcm.eventBus.InviteRequestEvent
import com.fcmchat.fcmchat.fcm.eventBus.InviteResponseEvent
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * Created by Voronin Igor on 09.08.2018.
 */
abstract class MainActivity : MvpAppCompatActivity() {

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun inviteRequest(request: InviteRequestEvent) = showReqAlertDialog(request)

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun inviteResponse(response: InviteResponseEvent) = showInviteResponseAlertDialog(response)

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
        view.findViewById<TextView>(R.id.user_name).text = response.userName
        view.findViewById<TextView>(R.id.chain_name).text = response.chainName

        return view
    }

    abstract fun newUserAdded(view: View, response: InviteResponseEvent)

    abstract fun acceptInvitation(request: InviteRequestEvent)

    private fun showReqAlertDialog(request: InviteRequestEvent) {
        val view = createReqInviteView(request)

        AlertDialog.Builder(this)
                .setView(view)
                .setPositiveButton("Accept") { _, _ -> acceptInvitation(request) }
                .setNegativeButton("Cancel") { _, _ -> }
                .create()
                .show()
    }

    private fun createReqInviteView(request: InviteRequestEvent): View {
        val view = LayoutInflater.from(this).inflate(R.layout.invite_alert_dialog, null, false)
        val chainNameTv = view.findViewById<TextView>(R.id.chain_name)!!
        val userNameTv = view.findViewById<TextView>(R.id.user_name)!!

        chainNameTv.text = request.chainName
        userNameTv.text = request.userName

        return view
    }
}