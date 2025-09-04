package com.open.warehouseandinventory.service.strategy

import com.open.warehouseandinventory.connector.ProductConnector

interface ConnectorSelectionStrategy {
    fun select(barcode: String): ProductConnector
}
