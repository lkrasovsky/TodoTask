package com.example.todotask.retrofit

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class TodoService {

    private var mRetrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(createClient())
        .build()

    private val todoApi: TodoApi by lazy {
        mRetrofit.create(TodoApi::class.java)
    }

    private val loggingInterceptor: Interceptor by lazy {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        loggingInterceptor
    }

    private fun createClient(): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder().addInterceptor(loggingInterceptor)
        return clientBuilder.build()
    }

    companion object {
        const val BASE_URL = "https://gorest.co.in/public-api"
    }
}