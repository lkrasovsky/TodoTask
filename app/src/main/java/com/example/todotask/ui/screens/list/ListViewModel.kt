package com.example.todotask.ui.screens.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todotask.retrofit.TodoRepository
import com.example.todotask.ui.adapter.TodosAdapter
import com.example.todotask.utils.extensions.invoke

class ListViewModel(private val todoRepository: TodoRepository) : ViewModel() {

    var todosLiveData: LiveData<List<TodosAdapter.TodoItem>> = MutableLiveData()
    var isLoadingLiveData: LiveData<Boolean> = MutableLiveData()
    var errorMessageLiveData: LiveData<String> = MutableLiveData()

    fun loadTodos() {
        (isLoadingLiveData as MutableLiveData).postValue(true)

        todoRepository.getTodos().invoke(
            onResponseSuccessful = { response ->
                val todos = response.body()?.todos ?: return@invoke
                val todoItems = todos.map {
                    TodosAdapter.TodoItem(it.id, it.title)
                }
                (todosLiveData as MutableLiveData).postValue(todoItems)
            },
            onResponseNotSuccessful = {
                (errorMessageLiveData as MutableLiveData).postValue("Couldn't get your todos")
            },
            onFailure = {
                (errorMessageLiveData as MutableLiveData).postValue("Failed. Check your internet connection.")
            },
            anyway = {
                (isLoadingLiveData as MutableLiveData).postValue(false)
            }
        )
    }
}