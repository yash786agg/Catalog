package com.app.catalog.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.catalog.commons.callBacks.DiffUtilCallBack
import com.app.catalog.R
import com.app.catalog.databinding.AdapterProductListBinding
import com.app.catalog.commons.extensions.ProductItem
import com.app.catalog.domain.entities.Products
import kotlin.properties.Delegates

class ProductListAdapter : RecyclerView.Adapter<ProductListAdapter.MyViewHolder>(),
    DiffUtilCallBack {

    private var onProductItemClickListener : ProductItem? = null

    var items : List<Products> by Delegates.observable(emptyList()) { _, oldItem, newItem ->
        autoNotify(oldItem, newItem) { old, new -> old.productId == new.productId }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding: AdapterProductListBinding = DataBindingUtil
            .inflate(LayoutInflater.from(parent.context), R.layout.adapter_product_list, parent, false)

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(items[position],onProductItemClickListener)
    }

    // Gets the number of Items in the list
    override fun getItemCount(): Int = items.size

    fun setonProjectItemClickListener(onProductItemClickListener : ProductItem) {
        this.onProductItemClickListener = onProductItemClickListener
    }

    inner class MyViewHolder(private val binding: AdapterProductListBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Products, clickListener: ProductItem?) {
            binding.product = product
            itemView.setOnClickListener { clickListener?.onProductItemClickListener(product) }
        }
    }
}
