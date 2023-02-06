package br.com.digio.androidtest

import android.app.Application
import br.com.digio.androidtest.di.appModule
import br.com.digio.androidtest.presentation.MainViewModel
import br.com.digio.androidtest.repository.*
import br.com.digio.androidtest.util.BASE_URL
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MainApplication : Application() {



    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(appModule)
        }
    }
}