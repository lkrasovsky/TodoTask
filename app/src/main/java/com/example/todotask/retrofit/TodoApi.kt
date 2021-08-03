package com.example.todotask.retrofit

import com.example.todotask.retrofit.entity.TodosResponse
import retrofit2.Call
import retrofit2.http.GET

interface TodoApi {

    @GET("todos")
    fun getTodos(): Call<TodosResponse>
}