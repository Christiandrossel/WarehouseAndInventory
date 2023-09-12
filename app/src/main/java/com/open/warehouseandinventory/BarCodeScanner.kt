package com.open.warehouseandinventory

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult

class BarCodeScanner(
    private val savedInstanceState: Bundle?,
    private val activity: Activity
) {

    private var integrator: IntentIntegrator = IntentIntegrator(activity)

    init {
        // Start the Barcode-Scanner
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
        integrator.setPrompt("Barcode scannen")
        integrator.setCameraId(0)  // Verwende die hintere Kamera
        integrator.setBeepEnabled(true)
        // integrator.setCaptureActivity(CaptureActivityPortrait::class.java)
        integrator.setOrientationLocked(false)  // Schalte die Ausrichtungssperre aus
        integrator.initiateScan()
    }

    // override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    fun getResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            if (result.contents == null) {
                Toast.makeText(activity.applicationContext, "Scan abgebrochen", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(activity.applicationContext, "Gescannt: " + result.contents, Toast.LENGTH_LONG).show()
            }
        }
    }

}