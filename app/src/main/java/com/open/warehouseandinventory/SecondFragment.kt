package com.open.warehouseandinventory

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.open.warehouseandinventory.databinding.FragmentSecondBinding
import com.open.warehouseandinventory.model.Product
import com.open.warehouseandinventory.model.ProductLiveData
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
//        val product = ProductLiveData( barcode = MutableLiveData("1234"),
//            name = MutableLiveData("Product 1"), quantity =  MutableLiveData("10"), description = MutableLiveData("This is product 1"))
//        val product = productViewModel
        productViewModel = ViewModelProvider(requireActivity()).get(ProductViewModel::class.java)
        binding.product = productViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSave.setOnClickListener {
//            productViewModel.updateProductList()
//            productService.saveProduct(productViewModel.product.value!!)
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        binding.buttonCancel.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        binding.buttonIncrement.setOnClickListener {
            // add 1 to quantity
//            productViewModel.quantity.value =
//                (productViewModel.quantity.value?.toInt()?.plus(1)).toString()
        }

        binding.buttonDecrement.setOnClickListener {
            // subtract 1 from quantity
//            productViewModel.quantity.value =
//                (productViewModel.quantity.value?.toInt()?.minus(1)).toString()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}