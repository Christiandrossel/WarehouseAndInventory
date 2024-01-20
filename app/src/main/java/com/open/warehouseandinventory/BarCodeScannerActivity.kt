package com.open.warehouseandinventory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.journeyapps.barcodescanner.CaptureManager
import com.journeyapps.barcodescanner.DecoratedBarcodeView
import com.open.warehouseandinventory.service.v2.BarcodeScannerV2

//import com.open.warehouseandinventory.service.BarCodeScanner

class BarCodeScannerActivity : AppCompatActivity() {

//    private lateinit var barCodeScanner: BarCodeScanner
    private lateinit var barCodeScanner: BarcodeScannerV2

//    private lateinit var capture: CaptureManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bar_code_scanner)
        Log.d("BARCODE", "BarCodeScannerActivity was created!")
//        barCodeScanner = BarCodeScanner(savedInstanceState, this)
//        barCodeScanner = BarcodeScannerV2(this, productViewModel) {
//            Log.d("BARCODE", "BarcodeScannerV2 returned: $it")
//            val intent = Intent(this, MainActivity::class.java)
//            intent.putExtra("BARCODE", it)
//            startActivity(intent)
//        }
    }


    // destroy the activity after scanning
    override fun onDestroy() {
        super.onDestroy()
        Log.d("BARCODE", "BarCodeScannerActivity was destroyed!")
        finish()
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {   // nach dem scannen schließt sich die activity nicht
//        super.onActivityResult(requestCode, resultCode, data)
//        barCodeScanner.getResult(requestCode, resultCode, data)
//    }

}