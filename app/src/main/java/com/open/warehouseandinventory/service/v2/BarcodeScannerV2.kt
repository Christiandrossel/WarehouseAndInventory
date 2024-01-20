package com.open.warehouseandinventory.service.v2

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
import androidx.navigation.findNavController
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning
import com.open.warehouseandinventory.R
import com.open.warehouseandinventory.model.Product
import com.open.warehouseandinventory.model.viewmodel.ProductViewModel

/**
 * This is a BarcodeScanner from Google mlkit Barcode Scanner library.
 * Docu: https://developers.google.com/ml-kit/vision/barcode-scanning/code-scanner?hl=de
 */
class BarcodeScannerV2(context: Context, productViewModel: ProductViewModel, onBarcodeScanned: (String) -> Unit) {

    init {
        Log.d("BARCODE", "BarcodeScannerV2 was initialized!")
        val options = generateGmsBarcodeScannerOptions()
        val scanner = GmsBarcodeScanning.getClient(context, options)

        scanner.startScan()
            .addOnSuccessListener { barcode ->
                // Task completed successfully
                val rawValue: String? = barcode.rawValue
                rawValue?.let {
                    Log.d("BARCODE", "Scan was successful!, $rawValue")
                    onBarcodeScanned(rawValue)
                } ?: run {
                    Log.d("BARCODE", "Scan was successful!, but rawValue is null")
                    productViewModel.setProduct(Product())
                }
            }
            .addOnCanceledListener {
                // Task canceled
                Log.d("BARCODE", "Scan was canceled!")
                productViewModel.setProduct(Product())
            }
            .addOnFailureListener { e ->
                // Task failed with an exception
                Log.d("BARCODE", "Scan failed!")
                e.printStackTrace()
                productViewModel.setProduct(Product())
            }


    }

    /**
     * This method generates the options for the BarcodeScanner.
     * Here is defined which barcode formats are supported.
     */
    private fun generateGmsBarcodeScannerOptions() = GmsBarcodeScannerOptions.Builder()
        .setBarcodeFormats(
            Barcode.FORMAT_CODE_128,
            Barcode.FORMAT_CODE_93,
            Barcode.FORMAT_CODE_39,
            Barcode.FORMAT_EAN_8,
            Barcode.FORMAT_EAN_13,
            Barcode.FORMAT_UPC_A,
            Barcode.FORMAT_UPC_E,
            Barcode.FORMAT_ITF
        )
        .enableAutoZoom()   // available on 16.1.0 and higher
        .build()
        .also { Log.d("BARCODE", "GmsBarcodeScannerOptions was generated!") }
}