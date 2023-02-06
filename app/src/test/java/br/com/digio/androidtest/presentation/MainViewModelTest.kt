package br.com.digio.androidtest.presentation

import androidx.lifecycle.MutableLiveData
import br.com.digio.androidtest.domain.DigioProducts
import br.com.digio.androidtest.repository.DigioRepository
import br.com.digio.androidtest.util.ProductsFactory
import br.com.digio.androidtest.util.Resultado
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert
import org.junit.Before
import org.junit.Test


internal class MainViewModelTest {

    private val repository = mockk<DigioRepository>()

    private lateinit var viewModel: MainViewModel

    private val products = MutableLiveData<Resultado<DigioProducts?>>(Resultado.Sucesso(ProductsFactory.DigioProducts))

    @Before
    fun setup() {
        viewModel = MainViewModel(repository)
    }

    @Test
    fun `when getProducts is called, then repository should return products`() {
        every {  viewModel.getProducts() } returns products

        val result = viewModel.getProducts()

        verify { viewModel.getProducts() }
        Assert.assertEquals(result, products)

    }
}