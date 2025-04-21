package com.open.warehouseandinventory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceFragmentCompat
import com.open.warehouseandinventory.databinding.FragmentCsvExportBinding
import com.open.warehouseandinventory.model.Product
import com.open.warehouseandinventory.model.viewmodel.ProductViewModel
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CSVExportFragment : Fragment() {

    private lateinit var productViewModel: ProductViewModel
    private var _binding: FragmentCsvExportBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCsvExportBinding.inflate(inflater, container, false)
        productViewModel = ViewModelProvider(requireActivity())[ProductViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupExportButtons()
        observerProducts()
    }

    private fun setupExportButtons() {
        binding.btnExportCsv.setOnClickListener {
            exportToCSV()
        }
    }

    private fun observerProducts() {
        productViewModel.products.observe(viewLifecycleOwner) { products ->
            if (products.isNullOrEmpty()) {
                binding.tvExportStatus.visibility = View.VISIBLE
                binding.tvExportStatus.text = getString(R.string.no_products_to_export)
                binding.btnExportCsv.isEnabled = false
            } else {
                binding.btnExportCsv.isEnabled = true
            }
        }
    }

    private fun exportToCSV() {
        val products = productViewModel.products.value ?: return

        binding.progressBar.visibility = View.VISIBLE
        binding.tvExportStatus.visibility = View.VISIBLE

        try {
            val csvContent = buildCsvContent(products, binding.cbIncludeHeader.isChecked)
            val filePath = saveCsvToFile(csvContent)

            binding.tvExportStatus.text = getString(R.string.export_success, filePath)
            binding.tvExportStatus.setTextColor(ContextCompat.getColor(requireContext(), R.color.success))
        } catch (e: Exception) {
            binding.tvExportStatus.text = getString(R.string.export_failed, e.localizedMessage)
            binding.tvExportStatus.setTextColor(ContextCompat.getColor(requireContext(), R.color.error))
        } finally {
            binding.progressBar.visibility = View.GONE
            binding.tvExportStatus.visibility = View.VISIBLE
        }
    }

    private fun buildCsvContent(products: List<Product>, includeHeader: Boolean): String {
        val stringBuilder = StringBuilder()

        if (includeHeader) {
            stringBuilder.append("ID,Barcode,Name,Quantity,Description\n")
        }

        products.forEach { product ->
            stringBuilder.append("${product.id},")
            stringBuilder.append("\"${product.barcode ?: ""}\",")
            stringBuilder.append("\"${product.name ?: ""}\",")
            stringBuilder.append("${product.quantity},")
            stringBuilder.append("\"${product.description ?: ""}\"\n")
        }

        return stringBuilder.toString()
    }

    private fun saveCsvToFile(content: String): String {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val fileName = "products_export_$timeStamp.csv"
        val file = File(requireContext().getExternalFilesDir(null), fileName)
        file.writeText(content)
        return file.absolutePath
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}