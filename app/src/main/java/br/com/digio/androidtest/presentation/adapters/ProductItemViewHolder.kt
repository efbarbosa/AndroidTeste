package br.com.digio.androidtest.presentation.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import br.com.digio.androidtest.R
import br.com.digio.androidtest.databinding.ItemMainProductsBinding

import br.com.digio.androidtest.domain.Product
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

import java.lang.Exception

class ProductItemViewHolder constructor(
    private val binding: ItemMainProductsBinding
) : RecyclerView.ViewHolder(binding.root) {


    fun bind(product: Product) {

        binding.imgMainItemProducts.contentDescription = product.name
        binding.progressImage.visibility = View.VISIBLE

        Picasso.get()
            .load(product.imageURL)
            .error(R.drawable.ic_alert_circle)
            .into(binding.imgMainItemProducts, object : Callback {
                override fun onSuccess() {
                    binding.progressImage.visibility = View.GONE
                }

                override fun onError(e: Exception?) {
                    binding.progressImage.visibility = View.GONE
                }
            })
    }
}
