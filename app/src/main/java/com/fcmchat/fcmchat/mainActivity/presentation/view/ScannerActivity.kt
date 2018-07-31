package com.fcmchat.fcmchat.mainActivity.presentation.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import butterknife.BindView
import butterknife.ButterKnife
import com.fcmchat.fcmchat.R
import me.dm7.barcodescanner.zbar.BarcodeFormat
import me.dm7.barcodescanner.zbar.Result
import me.dm7.barcodescanner.zbar.ZBarScannerView


class ScannerActivity : AppCompatActivity(), ZBarScannerView.ResultHandler {

    @BindView(R.id.scanner_view) lateinit var scannerView: ZBarScannerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scanner)
        ButterKnife.bind(this)
        scannerView.setFormats(BarcodeFormat.ALL_FORMATS)

        initToolbar()
    }

    public override fun onResume() {
        super.onResume()
        scannerView.setResultHandler(this)
        scannerView.startCamera()
    }

    public override fun onPause() {
        super.onPause()
        scannerView.stopCamera()
    }

    override fun handleResult(result: Result) {
        val intent = Intent()
        intent.putExtra("code", result.contents)
        setResult(Activity.RESULT_OK, intent)
        onBackPressed()
    }

    private fun initToolbar() {
        supportActionBar?.title = "Scan Qr-code"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                setResult(Activity.RESULT_CANCELED)
                onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
