package com.fcmchat.fcmchat.invite.presentation.view

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.fcmchat.fcmchat.R
import com.fcmchat.fcmchat.invite.presentation.presenter.InvitePresenter
import net.glxn.qrgen.android.QRCode

class InviteView : MvpAppCompatFragment(), IInviteActivityView {

    @InjectPresenter lateinit var presenter: InvitePresenter

    @Nullable @BindView(R.id.firebase_id_edit_text) lateinit var tokenEditText: EditText
    @Nullable @BindView(R.id.device_id) lateinit var deviceIdTv: TextView
    @Nullable @BindView(R.id.chains_list_spinner) lateinit var chainsSpinner: Spinner

    override fun setChainsList(list: ArrayList<String>) {
        val spinnerAdapter = ArrayAdapter(context!!, android.R.layout.simple_list_item_1, list)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        chainsSpinner.adapter = spinnerAdapter
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.invite_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ButterKnife.bind(this, view)
        presenter.viewCreated()
    }

    override fun setDeviceId(deviceID: String) {
        deviceIdTv.text = deviceID
    }

    override fun showQrCode(firebaseToken: String) {
        val bitmap: Bitmap = QRCode.from(firebaseToken).withSize(1000, 1000).bitmap()
        val view: View = LayoutInflater.from(activity!!).inflate(R.layout.qr_screen, null)
        val imageView: ImageView = view.findViewById(R.id.qr_code_image_view)

        imageView.setImageBitmap(bitmap)

        AlertDialog.Builder(activity!!).setView(view).create().show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == AppCompatActivity.RESULT_OK) {
            val code: String = data?.getStringExtra("code")!!
            if (!code.isEmpty()) tokenEditText.setText(code)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            showQrScanner()
        }
    }

    override fun showAlert(message: String) {
        AlertDialog.Builder(context!!)
                .setTitle("Alert!")
                .setMessage(message)
                .create()
                .show()
    }

    @Nullable @OnClick(R.id.send_invitation_btn) fun sendInvitationBtnClick() {
        presenter.sendInvitationBtnClick(
                tokenEditText.text.toString(),
                chainsSpinner.selectedItem?.toString()
        )
    }

    @OnClick(R.id.imageButton) fun showQrCode() = presenter.showQrCodeBtnClick()

    @OnClick(R.id.qr_code_scanner_btn) fun showQrScanner() {
        if (hasCameraPermission()) {
            startActivityForResult(Intent(activity!!, ScannerActivity::class.java), 0)
        }
    }

    private fun hasCameraPermission(): Boolean {
        val permissionState = ContextCompat.checkSelfPermission(activity!!, Manifest.permission.CAMERA)
        val hasPermission = permissionState == PackageManager.PERMISSION_GRANTED
        if (!hasPermission) {
            requestCameraPermission()
        }

        return hasPermission
    }

    private fun requestCameraPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity!!, Manifest.permission.CAMERA)) {
            showAlert("No Camera Permission")
        } else
            ActivityCompat.requestPermissions(activity!!, arrayOf(Manifest.permission.CAMERA), 0)
    }
}
