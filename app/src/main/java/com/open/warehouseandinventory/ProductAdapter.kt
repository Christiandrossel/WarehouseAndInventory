package com.open.warehouseandinventory

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.open.warehouseandinventory.model.Product

class ProductAdapter( private val productList: List<Product>) :RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

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
        val currentItem = productList[position]
        holder.barcode.text = currentItem.barcode
        holder.name.text = currentItem.name
        holder.quantity.text = currentItem.quantity.toString()
        holder.description.text = currentItem.description
    }

    override fun getItemCount() = productList.size
}