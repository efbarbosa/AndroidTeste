package br.com.digio.androidtest.repository


import br.com.digio.androidtest.domain.DigioProducts
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface DigioEndpoint {
    @GET("sandbox/products")
    suspend fun getProducts(): Response<DigioProducts>
}