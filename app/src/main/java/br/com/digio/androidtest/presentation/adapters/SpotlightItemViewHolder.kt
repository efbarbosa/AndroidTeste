package br.com.digio.androidtest.presentation.adapters

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import br.com.digio.androidtest.R
import br.com.digio.androidtest.databinding.ItemMainSpotlightBinding
import br.com.digio.androidtest.domain.Spotlight
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception
import kotlin.math.log

class SpotlightItemViewHolder constructor(
    private val binding: ItemMainSpotlightBinding
) : RecyclerView.ViewHolder(binding.root) {


    fun bind(product: Spotlight) {

        binding.imgMainItemSpotLight.contentDescription = product.name
        binding.progressImage.visibility = View.VISIBLE
    Log.e("EDILSON", product.name)
        Picasso.get()
            .load(product.bannerURL)
            .error(R.drawable.ic_alert_circle)
            .into(binding.imgMainItemSpotLight, object : Callback {
                override fun onSuccess() {
                    binding.progressImage.visibility = View.GONE
                }

                override fun onError(e: Exception?) {
                    binding.progressImage.visibility = View.GONE
                }
            })
    }
}
