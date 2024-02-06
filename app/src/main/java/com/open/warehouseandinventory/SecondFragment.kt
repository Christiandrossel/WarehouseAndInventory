package com.open.warehouseandinventory

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.open.warehouseandinventory.databinding.FragmentSecondBinding
import com.open.warehouseandinventory.model.viewmodel.ProductViewModel
import com.open.warehouseandinventory.service.ProductService

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment(), NavigationService {

    private lateinit var productViewModel: ProductViewModel
    private var _binding: FragmentSecondBinding? = null
    private val productService = ProductService.instance

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        productViewModel = ViewModelProvider(requireActivity())[ProductViewModel::class.java]
        binding.product = productViewModel
        changeProductScannerButton()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productObserver()

        saveButtonClickListener()
        cancelButtonClickListener()
        incrementButtonClickListener()
        decrementButtonClickListener()
    }

    private fun productObserver() {
        productViewModel.product.observe(viewLifecycleOwner) {
            binding.editTextBarcode.setText(it.barcode)
            binding.editTextName.setText(it.name)
            binding.editTextQuantity.setText(it.quantity.toString())
            binding.editTextDescription.setText(it.description)
        }
    }

    private fun decrementButtonClickListener() {
        binding.buttonDecrement.setOnClickListener {
            // subtract 1 from quantity
            productViewModel.updateQuantity((productViewModel.quantity.value?.toInt()?.minus(1)).toString())
            binding.editTextQuantity.setText(productViewModel.quantity.value)
        }
    }

    private fun incrementButtonClickListener() {
        binding.buttonIncrement.setOnClickListener {
            // add 1 to quantity
            productViewModel.updateQuantity((productViewModel.quantity.value?.toInt()?.plus(1)).toString())
            binding.editTextQuantity.setText(productViewModel.quantity.value)
        }
    }

    private fun cancelButtonClickListener() {
        binding.buttonCancel.setOnClickListener {
//            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
            navigateListProductsFragment(it)
        }
    }

    private fun saveButtonClickListener() {
        binding.buttonSave.setOnClickListener {
            updateProductViewModelFromUiView()
            if (productViewModel.isNotEmpty()) {
                productService.saveProduct(productViewModel.product.value!!)
                navigateListProductsFragment(it)
            } else {
                produceMissingFieldMessage()
            }
        }
    }

    private fun updateProductViewModelFromUiView() {
        productViewModel.product.value?.let {
            productViewModel.updateProducts(
                productViewModel.updateProduct(
                    barcode = binding.editTextBarcode.text.toString(),
                    name = binding.editTextName.text.toString(),
                    quantity = Integer.parseInt(binding.editTextQuantity.text.toString()),
                    description = binding.editTextDescription.text.toString()
                )
            )
        }
    }

    /**
     * Produces a message if the name or quantity field is empty.
     */
   private fun produceMissingFieldMessage() {
            Log.d("Product", "name and quantity must not be empty!")
            binding.editTextName.error = getString(R.string.error_empty_product_name)
            when {
                productViewModel.isQuantityEmpty() -> binding.editTextQuantity.error = getString(R.string.error_empty_quantity)
                productViewModel.isQuantityLowerThenZero() -> binding.editTextQuantity.error = getString(R.string.error_lower_null)
            }
        }



    private fun changeProductScannerButton() {
        val fab = requireActivity().findViewById<View>(R.id.fab)
        fab?.visibility = View.INVISIBLE
        fab?.isEnabled = false
    }

    override fun navigateEditProductFragment(view: View) {
        view.findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
    }

    override fun navigateListProductsFragment(view: View) {
        view.findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("SecondFragment", "onDestroyView")
        _binding = null
    }
}