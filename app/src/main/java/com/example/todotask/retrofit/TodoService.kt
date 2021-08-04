package com.example.todotask.retrofit

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class TodoService(private val context: Context) {

    val todoApi: TodoApi by lazy {
        mRetrofit.create(TodoApi::class.java)
    }

    private var mRetrofit: Retrofit

    private val loggingInterceptor: Interceptor by lazy {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        loggingInterceptor
    }

    private val networkInterceptor = Interceptor { chain ->
        val cacheControl: CacheControl = CacheControl.Builder()
            .maxAge(7, TimeUnit.SECONDS)
            .build()

        val response: Response = chain.proceed(chain.request())

        response.newBuilder()
            .removeHeader(HEADER_PRAGMA)
            .removeHeader(HEADER_CACHE_CONTROL)
            .header(HEADER_CACHE_CONTROL, cacheControl.toString())
            .build()
    }

    private val offlineInterceptor = Interceptor { chain ->
        var request: Request = chain.request()
        if (!isNetworkConnected) {
            val cacheControl: CacheControl = CacheControl.Builder()
                .maxStale(7, TimeUnit.DAYS)
                .build()

            request = request.newBuilder()
                .removeHeader(HEADER_PRAGMA)
                .removeHeader(HEADER_CACHE_CONTROL)
                .cacheControl(cacheControl)
                .build()
        }
        chain.proceed(request)
    }

    private val isNetworkConnected: Boolean
        get() {
            val type = runBlocking(Dispatchers.Default) {
                var result = 0
                val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
                cm?.getNetworkCapabilities(cm.activeNetwork)?.run {
                    result = when {
                        hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> 2
                        hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> 1
                        hasTransport(NetworkCapabilities.TRANSPORT_VPN) -> 3
                        else -> 0
                    }
                }

                result
            }
            return type != 0
        }

    private val cache: Cache
        get() = Cache(context.cacheDir, 300 * 1024 * 1024) // 300MB

    init {
        mRetrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(createClient())
            .build()
    }

    private fun createClient(): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
            .cache(cache)
            .addNetworkInterceptor(networkInterceptor)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(offlineInterceptor)
        return clientBuilder.build()
    }

    companion object {
        const val BASE_URL = "https://gorest.co.in/public-api/"

        const val HEADER_CACHE_CONTROL = "Cache-Control"
        const val HEADER_PRAGMA = "Pragma"
    }
}