package com.open.warehouseandinventory

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.open.warehouseandinventory.model.Product
import com.open.warehouseandinventory.model.viewmodel.ProductViewModel

class ProductAdapter(
    private val productViewModel: ProductViewModel,
    private val onDeleteListener: (position: Int) -> Unit
) :RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val barcode: TextView = itemView.findViewById(R.id.barcode)
        val name: TextView = itemView.findViewById(R.id.name)
        val quantity: TextView = itemView.findViewById(R.id.quantity)
        val description: TextView = itemView.findViewById(R.id.description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_cardview, parent, false)
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

    // Delete item from list
    fun removeItem(position: Int) {
        productViewModel.removeAt(position)
        notifyItemRemoved(position)
    }

    // here the onDeleteListener is called when a card is swiped left or right
    fun onSwipeToDelete(position: Int) {
        onDeleteListener.invoke(position)
    }
}