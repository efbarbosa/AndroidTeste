package br.com.digio.androidtest.presentation

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.digio.androidtest.R
import br.com.digio.androidtest.databinding.ActivityMainBinding
import br.com.digio.androidtest.presentation.adapters.ProductAdapter
import br.com.digio.androidtest.presentation.adapters.SpotlightAdapter
import br.com.digio.androidtest.util.Resultado
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(R.layout.activity_main) {



    private val viewModel: MainViewModel  by viewModel()
    private lateinit var binding: ActivityMainBinding

    private val productAdapter: ProductAdapter by lazy {
        ProductAdapter()
    }

    private val spotlightAdapter: SpotlightAdapter by lazy {
        SpotlightAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupDigioCashText()
        observers()
    }

    private fun observers() {
        viewModel.getProducts().observe(this) { resultDigioProducts ->
            when (resultDigioProducts) {
                is Resultado.Erro -> {
                    val message = getString(R.string.error)

                    binding.loadDigioContainer.root.visibility = View.GONE
                    binding.body.visibility = View.GONE

                    Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
                }
                is Resultado.Sucesso -> {
                    binding.loadDigioContainer.root.visibility = View.GONE
                    binding.body.visibility = View.VISIBLE

                    resultDigioProducts.dado?.let {
                        productAdapter.products = it.products
                        spotlightAdapter.spotlights = it.spotlight
                    }
                }
                is Resultado.Carregando ->{
                    binding.loadDigioContainer.root.visibility = View.VISIBLE
                }
            }

        }
    }



    private fun setupView() {
        binding.recyMainProducts.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyMainProducts.adapter = productAdapter

        binding.recyMainSpotlight.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyMainSpotlight.adapter = spotlightAdapter

        binding.body.visibility = View.GONE
        binding.loadDigioContainer.root.visibility = View.VISIBLE
    }

    private fun setupDigioCashText() {
        val digioCacheText = resources.getString(R.string.digio_cash_text)
        binding.txtMainDigioCash.text = SpannableString(digioCacheText).apply {
            setSpan(
                ForegroundColorSpan(
                    ContextCompat.getColor(this@MainActivity, R.color.blue_darker)
                ),
                0,
                5,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            setSpan(
                ForegroundColorSpan(
                    ContextCompat.getColor(this@MainActivity, R.color.font_color_digio_cash)
                ),
                6,
                digioCacheText.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
    }
}