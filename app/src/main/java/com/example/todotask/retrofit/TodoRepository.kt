package com.example.todotask.retrofit

import com.example.todotask.db.TodosDao
import com.example.todotask.retrofit.entity.Data
import com.example.todotask.retrofit.entity.TodosResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Call

class TodoRepository(private val service: TodoService, private val dao: TodosDao) {

    fun getTodos(): Call<TodosResponse> {
        return runBlocking(Dispatchers.IO) {
            service.todoApi.getTodos()
        }
    }

    fun getTodoById(id: Int): TodosResponse {
        return runBlocking(Dispatchers.IO) {
            dao.getTodoById(id)
        }
    }

    fun saveTodos(todos: List<Data>) {
        GlobalScope.launch(Dispatchers.IO) {
            dao.saveTodos(todos)
        }
    }
}