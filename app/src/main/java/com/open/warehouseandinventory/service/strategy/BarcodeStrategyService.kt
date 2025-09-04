package com.open.warehouseandinventory.service.strategy

import android.util.Log
import com.open.warehouseandinventory.connector.BarcodeLookupConnector
import com.open.warehouseandinventory.connector.ProductConnector
import com.open.warehouseandinventory.model.Product

//class BarcodeStrategyService(
//    private val connectorSelectionStrategy: ConnectorSelectionStrategy
//) {
//    suspend fun getProductByBarcode(barcode: String): Product? {
//        val connector = connectorSelectionStrategy.select(barcode)
//        return connector.getProduct(barcode)
//    }
//}


class BarcodeStrategyService(
    private val connector: ProductConnector
) {
    suspend fun getProductByBarcode(barcode: String, connector: ProductConnector): Product? {
        return try {
            val product = connector.getProduct(barcode)

            if (product != null) {
                Log.d(
                    "ProductFacadeService",
                    "Product found using ${connector::class.simpleName} for barcode: $barcode"
                )
            }
            product

        } catch (e: Exception) {
            Log.e(
                "ProductFacadeService",
                "Error with ${connector::class.simpleName} for barcode: $barcode",
                e
            )
            null
        }
    }
}