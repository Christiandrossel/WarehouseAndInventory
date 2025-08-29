package com.open.warehouseandinventory.service

import android.util.Log
import com.open.warehouseandinventory.connector.BarcodeLookupConnector
import com.open.warehouseandinventory.connector.DatakickConnector
import com.open.warehouseandinventory.connector.GtinSearchConnector
import com.open.warehouseandinventory.connector.ScanBotConnector // Annahme: Dieser Connector existiert und wurde angepasst
import com.open.warehouseandinventory.connector.ProductConnector // Importieren des Interfaces
import com.open.warehouseandinventory.model.Product

/**
 * This service acts as a facade to various product connectors.
 * It iterates through a list of available connectors to find product information.
 * This can be useful for managing API quotas or trying different data sources.
 */
class ProductFacadeService {

    // Instantiate connectors. Consider using Dependency Injection here for better testability.
    private val barcodeLookupConnector = BarcodeLookupConnector()
    private val datakickConnector = DatakickConnector()
    private val gtinSearchConnector = GtinSearchConnector()
    private val scanBotConnector = ScanBotConnector()

    // List of connectors to try in order.
    // The order here determines which connector is tried first.
    private val connectors: List<ProductConnector> = listOf(
        barcodeLookupConnector,
        datakickConnector,
        gtinSearchConnector,
        scanBotConnector
    )

    suspend fun getProduct(barcode: String): Product? {
        for (connector in connectors) {
            try {
                val product = connector.getProduct(barcode)
                if (product != null) {
                    Log.d("ProductFacadeService", "Product found using ${connector::class.simpleName} for barcode: $barcode")
                    return product
                }
            } catch (e: Exception) {
                Log.e("ProductFacadeService", "Error with ${connector::class.simpleName} for barcode: $barcode", e)
            }
        }
        Log.w("ProductFacadeService", "Product not found by any connector for barcode: $barcode")
        return null
    }
}
