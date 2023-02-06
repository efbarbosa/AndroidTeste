package br.com.digio.androidtest.util

sealed class Resultado<out R> {
    data class Sucesso<out T>(val dado: T?) : Resultado<T?>()
    data class Erro(val exception: Exception) : Resultado<Nothing>()
    class Carregando : Resultado<Nothing>()
}