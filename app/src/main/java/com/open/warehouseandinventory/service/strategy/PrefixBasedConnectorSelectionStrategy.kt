package com.open.warehouseandinventory.service.strategy


import com.open.warehouseandinventory.connector.ProductConnector

class PrefixBasedConnectorSelectionStrategy(
    private val rules: List<Rule>,
    private val defaultConnector: ProductConnector
) : ConnectorSelectionStrategy {

    data class Rule(
        val pattern: Regex,
        val connector: ProductConnector
    )

    override fun select(barcode: String): ProductConnector {
        return rules.firstOrNull { it.pattern.containsMatchIn(barcode) }?.connector
            ?: defaultConnector
    }
}