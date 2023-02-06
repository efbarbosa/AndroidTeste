package br.com.digio.androidtest.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import br.com.digio.androidtest.domain.DigioProducts
import br.com.digio.androidtest.repository.DigioRepository
import br.com.digio.androidtest.util.Resultado

class MainViewModel(private val repository: DigioRepository) : ViewModel() {
    fun getProducts() : LiveData<Resultado<DigioProducts?>> = repository.getProducts()
}

