package br.com.digio.androidtest.domain

data class DigioProducts (
    val cash: Cash,
    val products: List<Product>,
    val spotlight: List<Spotlight>
)