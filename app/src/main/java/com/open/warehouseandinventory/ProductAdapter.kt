package com.open.warehouseandinventory

import android.provider.Settings.System.getString
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.open.warehouseandinventory.model.Product
import com.open.warehouseandinventory.model.viewmodel.ProductViewModel
import com.open.warehouseandinventory.service.ProductService

class ProductAdapter(
    private val productViewModel: ProductViewModel,
    private val productService: ProductService,
    private val fragment: Fragment,
) :RecyclerView.Adapter<ProductAdapter.ViewHolder>() {


    private lateinit var view: View


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val barcode: TextView = itemView.findViewById(R.id.barcode)
        val name: TextView = itemView.findViewById(R.id.name)
        val quantity: TextView = itemView.findViewById(R.id.quantity)
        val description: TextView = itemView.findViewById(R.id.description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        view = LayoutInflater.from(parent.context).inflate(R.layout.product_cardview, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = productViewModel.products.value?.elementAt(position)
        holder.barcode.text = currentItem?.barcode
        holder.name.text = currentItem?.name
        holder.quantity.text = currentItem?.quantity.toString()
        holder.description.text = currentItem?.description

        currentCardClickListener(holder, currentItem)

    }

    private fun currentCardClickListener(
        holder: ViewHolder,
        currentItem: Product?
    ) {
        holder.itemView.setOnClickListener {
            holder.itemView.findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            currentItem?.let { productViewModel.setProduct(it) }
        }
    }

    override fun getItemCount() = productViewModel.products.value?.size ?: 0

    /**
     * This method is called when the user swipes a product to delete it
     */
    fun onSwipeToDelete(position: Int) {
        val productToDelete = productViewModel.getProduct(position)
            ?: throw ProductCanNotDeleteException(position, IllegalStateException())

        val snackbar = Snackbar.make(
            this.view,
            this.view.context.getString(R.string.toast_product_deleted, productToDelete.name),
            Snackbar.LENGTH_SHORT
        )
        snackbar.setAction(this.view.context.getString(R.string.undo_button)) {
            productViewModel.setProduct(productToDelete)
            productViewModel.addProductAt(position, productToDelete)
            productService.saveProduct(productToDelete)
            notifyItemInserted(position)
            snackbar.dismiss()
        }

        snackbar.setActionTextColor(ContextCompat.getColor(this.view.context, R.color.white))
        if (snackbar.isShown) {
            snackbar.dismiss()
        } else {
            snackbar.show()
        }

        productViewModel.removeAt(position)
        productService.deleteProduct(productToDelete)
        notifyItemRemoved(position)
    }
}