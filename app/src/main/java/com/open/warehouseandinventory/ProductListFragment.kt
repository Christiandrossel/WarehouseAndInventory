package com.open.warehouseandinventory

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.open.warehouseandinventory.databinding.FragmentProductListBinding
import com.open.warehouseandinventory.model.viewmodel.ProductViewModel
import com.open.warehouseandinventory.service.ProductService

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ProductListFragment : Fragment(), NavigationService {

    private lateinit var productViewModel: ProductViewModel
    private lateinit var adapter: ProductAdapter
    private val productService = ProductService.instance

    private var _binding: FragmentProductListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductListBinding.inflate(inflater, container, false)
        productViewModel = ViewModelProvider(requireActivity()).get(ProductViewModel::class.java)

        // Set product adapter and remove item
        adapter = ProductAdapter(productViewModel, productService , this)

        binding.productRecyclerView.adapter = adapter
        binding.productRecyclerView.layoutManager = LinearLayoutManager(context)
        changeProductScannerButton()

        val itemTouchHelper = ItemTouchHelper(SwipeGesture(adapter, this))
        itemTouchHelper.attachToRecyclerView(binding.productRecyclerView)

        observeProducts()
        return binding.root
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//
//        //TODO Listeneintrag anklicken um zu bearbeiten
          // TODO Listeneintrag swipen um zu löschen
          // TODO gedrückt halten um mehrere zu löschen

//    }

    @SuppressLint("NotifyDataSetChanged")
    private fun observeProducts() {
        productViewModel.products.observe(viewLifecycleOwner) {
            adapter.notifyDataSetChanged()
        }
    }

    private fun changeProductScannerButton() {
        val fab = requireActivity().findViewById<View>(R.id.fab)
        fab?.visibility = View.VISIBLE
        fab?.isEnabled = true
    }

    override fun navigateEditProductFragment(view: View) {
        view.findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
    }

    override fun navigateListProductsFragment(view: View) {
        view.findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}