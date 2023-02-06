package br.com.digio.androidtest.repository


import android.app.Application
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


fun provideRetrofit(baseUrl: String, okHttp: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(okHttp)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideOkHttpClient(
    cache: Cache,
    offlineCacheInterceptor: Interceptor
): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .addInterceptor(offlineCacheInterceptor)
        .cache(cache).build()
}

fun provideCache(application: Application): Cache {
    val cacheSize = 10 * 1024 * 1024
    return Cache(application.cacheDir, cacheSize.toLong())
}

fun provideServices(retrofit: Retrofit): DigioEndpoint {
    return retrofit.create(DigioEndpoint::class.java)
}

fun provideOfflineCacheInterceptor(): Interceptor {
    return Interceptor { chain ->
        try {
            return@Interceptor chain.proceed(chain.request())
        } catch (e: Exception) {
            val cacheControl = CacheControl.Builder()
                .onlyIfCached()
                .maxStale(1, TimeUnit.DAYS)
                .build()
            val offlineRequest: Request = chain.request().newBuilder()
                .cacheControl(cacheControl)
                .removeHeader("Pragma")
                .build()
            return@Interceptor chain.proceed(offlineRequest)
        }
    }
}