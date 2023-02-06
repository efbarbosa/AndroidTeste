package br.com.digio.androidtest.repository

import android.content.Context
import androidx.lifecycle.liveData
import br.com.digio.androidtest.R
import br.com.digio.androidtest.util.Resultado
import kotlinx.coroutines.Dispatchers

class DigioRepository(private val apiDigio : DigioEndpoint, private val context : Context) {

    fun getProducts() = liveData(context = Dispatchers.IO){
        try {
            emit(Resultado.Carregando())
            val response = apiDigio.getProducts()
            if (response.isSuccessful) {
                emit(Resultado.Sucesso(dado = response.body()))
            } else {
                emit(Resultado.Erro(exception = Exception( context.getString(R.string.message_error_api) )))
            }
        }catch (exception : Exception){
            emit(Resultado.Erro(exception = exception))
        }
    }
}