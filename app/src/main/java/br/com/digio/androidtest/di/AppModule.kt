package br.com.digio.androidtest.di

import br.com.digio.androidtest.presentation.MainViewModel
import br.com.digio.androidtest.repository.*
import br.com.digio.androidtest.util.BASE_URL
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

 val appModule = module {
    single { provideCache(androidApplication()) }
    single { provideOkHttpClient(get(), get()) }
    single { provideOfflineCacheInterceptor() }
    single { provideRetrofit(BASE_URL, get()) }
    single { provideServices(get()) }
    single { DigioRepository(get(), androidApplication()) }
    viewModel { MainViewModel(get()) }
}