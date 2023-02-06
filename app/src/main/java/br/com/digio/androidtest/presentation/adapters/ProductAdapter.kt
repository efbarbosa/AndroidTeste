package br.com.digio.androidtest.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.digio.androidtest.databinding.ItemMainProductsBinding
import br.com.digio.androidtest.domain.Product

class ProductAdapter : RecyclerView.Adapter<ProductItemViewHolder>() {

    var products = emptyList<Product>()
        set(value) {
            val result = DiffUtil.calculateDiff(
                ProductListDiffCallback(
                    field,
                    value
                )
            )
            result.dispatchUpdatesTo(this)
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductItemViewHolder {
        return ProductItemViewHolder(
            ItemMainProductsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ProductItemViewHolder, position: Int) {
        holder.bind(products[position])
    }

    override fun getItemCount(): Int =
        products.size
}