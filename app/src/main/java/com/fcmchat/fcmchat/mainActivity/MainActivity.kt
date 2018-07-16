package com.fcmchat.fcmchat.mainActivity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.fcmchat.fcmchat.R

class MainActivity : MvpAppCompatActivity(), IMainActivityView {

    @InjectPresenter lateinit var presenter: MainActivityPresenter

    @BindView(R.id.send_message_btn) lateinit var btn: Button
    @BindView(R.id.firebase_id_edit_text) lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }

    @OnClick(R.id.send_message_btn) fun sendBtnClick() {
        presenter.sendMessageBtnClick(editText.text.toString())
    }
}
