package com.open.warehouseandinventory

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.open.warehouseandinventory.databinding.FragmentFirstBinding
import com.open.warehouseandinventory.model.Product
import com.open.warehouseandinventory.model.viewmodel.ProductViewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private lateinit var productViewModel: ProductViewModel

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        productViewModel = ViewModelProvider(requireActivity()).get(ProductViewModel::class.java)
        val adapter = ProductAdapter(productViewModel.allProducts.value ?: setOf())
        binding.productRecyclerView.adapter = adapter
        binding.productRecyclerView.layoutManager = LinearLayoutManager(context)
        
        return binding.root
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//
//        //TODO Listeneintrag anklicken
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}