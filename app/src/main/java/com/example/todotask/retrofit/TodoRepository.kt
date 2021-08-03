package com.example.todotask.retrofit

import com.example.todotask.retrofit.entity.TodosResponse
import retrofit2.Call

class TodoRepository(private val service: TodoService) {

    fun getTodos(): Call<TodosResponse> {
        return service.todoApi.getTodos()
    }
}