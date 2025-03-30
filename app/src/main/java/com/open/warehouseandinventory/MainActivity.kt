package com.open.warehouseandinventory

import android.content.Context
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.google.android.material.snackbar.Snackbar
import com.open.warehouseandinventory.databinding.ActivityMainBinding
import com.open.warehouseandinventory.model.Constant
import com.open.warehouseandinventory.model.viewmodel.ProductViewModel
import com.open.warehouseandinventory.service.ProductService
import com.open.warehouseandinventory.service.SettingsService
import com.open.warehouseandinventory.service.v2.BarcodeScannerV2
import com.open.warehouseandinventory.test.TestdataService
import io.paperdb.Paper
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), NavigationService {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var productViewModel: ProductViewModel
    private val productService = ProductService.instance
    private val testDataService = TestdataService.instance
    private val Context.dataStore by preferencesDataStore(name = Constant.SETTINGS)
    private val settingsService: SettingsService by lazy {
        SettingsService.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        instanciateDatabase(this)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        productViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)

        isTestingEnabled(false)
        addTestData()

        productViewModel.addAllProducts(productService.getAllProducts())
        //productViewModel.setProduct(productService.getProduct("1234")!!)

        val barcode = readBarcodeFromIntent()

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        val view = findViewById<View?>(android.R.id.content)
        if (barcode != null) {
            productViewModel.setProduct(productService.getProduct(barcode))
            navigateEditProductFragment(view)
        }

        barcodeScannerClickListener()
    }

    private fun barcodeScannerClickListener() {
        binding.fab.setOnClickListener {
            BarcodeScannerV2(this, productViewModel) { barcode ->
                productViewModel.setProduct(productService.getProduct(barcode))
            }
            navigateEditProductFragment(it)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            R.id.action_csv_export -> {
                navigateCSVExportFragment(this.findViewById(android.R.id.content))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    fun readBarcodeFromIntent(): String? {
        val barcode = intent.getStringExtra(getString(R.string.intent_barcode))
        if (barcode != null) {
            Snackbar.make(binding.root, "Barcode: $barcode", Snackbar.LENGTH_LONG).show()
        }
        return barcode
    }

    /**
     * Open the list of products fragment
     */
    override fun navigateListProductsFragment(view: View) {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        navController.navigate(R.id.action_SecondFragment_to_FirstFragment)
    }

    /**
     * Open the edit product fragment
     */
    override fun navigateEditProductFragment(view: View) {
        val newNavController = findNavController(R.id.nav_host_fragment_content_main)
        newNavController.navigate(R.id.action_FirstFragment_to_SecondFragment)
    }

    /**
     * Open the CSV export fragment
     */
    override fun navigateCSVExportFragment(view: View) {
        val newNavController = findNavController(R.id.nav_host_fragment_content_main)
        newNavController.navigate(R.id.action_FirstFragment_to_CSVExportFragment)
    }

    private fun instanciateDatabase(activity: MainActivity) {
        Paper.init(activity)
    }

    private fun addTestData() {
        lifecycleScope.launch {
            settingsService.getIsTestingEnabled().collect(
                FlowCollector {
                    if (it) {
                        testDataService.addTestData()
                    }
                }
            )
        }
    }

    private fun isTestingEnabled(isTestingEnabled: Boolean) {
        lifecycleScope.launch {
            settingsService.saveIsTestingEnabled(isTestingEnabled)
        }
    }
}