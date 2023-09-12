package com.open.warehouseandinventory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class BarCodeScannerActivity : AppCompatActivity() {

    private lateinit var barCodeScanner: BarCodeScanner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bar_code_scanner)
        barCodeScanner = BarCodeScanner(savedInstanceState, this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        barCodeScanner.getResult(requestCode, resultCode, data)
    }
}