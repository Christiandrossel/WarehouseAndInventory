package com.open.warehouseandinventory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.open.warehouseandinventory.databinding.FragmentSecondBinding
import com.open.warehouseandinventory.model.viewmodel.ProductViewModel
import com.open.warehouseandinventory.service.ProductService

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private lateinit var productViewModel: ProductViewModel
    private var _binding: FragmentSecondBinding? = null
    private val productService = ProductService()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        productViewModel = ViewModelProvider(requireActivity()).get(ProductViewModel::class.java)
        binding.product = productViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSave.setOnClickListener {
            // If product not null then copy it and update it
            productViewModel.product.value?.let {
                productViewModel.updateProductList(
                    productViewModel.updateProduct(
                        barcode = binding.editTextBarcode.text.toString(),
                        name = binding.editTextName.text.toString(),
                        quantity = Integer.parseInt(binding.editTextQuantity.text.toString()),
                        description = binding.editTextDescription.text.toString()
                    )
                )
            }
            productService.saveProduct(productViewModel.product.value!!)
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        binding.buttonCancel.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        binding.buttonIncrement.setOnClickListener {
            // add 1 to quantity
            productViewModel.updateQuantity((productViewModel.quantity.value?.toInt()?.plus(1)).toString())
            binding.editTextQuantity.setText(productViewModel.quantity.value)
        }

        binding.buttonDecrement.setOnClickListener {
            // subtract 1 from quantity
            productViewModel.updateQuantity((productViewModel.quantity.value?.toInt()?.minus(1)).toString())
            binding.editTextQuantity.setText(productViewModel.quantity.value)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}