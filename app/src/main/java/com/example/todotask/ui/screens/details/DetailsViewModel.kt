package com.example.todotask.ui.screens.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todotask.retrofit.TodoRepository
import com.example.todotask.retrofit.entity.Data

class DetailsViewModel(private val todoRepository: TodoRepository) : ViewModel() {

    val todoLiveData: LiveData<Data?> = MutableLiveData(null)
    val isLoadingLiveData: LiveData<Boolean> = MutableLiveData()
    val errorMessageLiveData: LiveData<String?> = MutableLiveData()

    fun loadTodo(todoId: Int) {
        try {
            (isLoadingLiveData as MutableLiveData).postValue(true)
            (errorMessageLiveData as MutableLiveData).postValue(null)

            val todo = todoRepository.getTodoById(todoId)
            (todoLiveData as MutableLiveData).postValue(todo)

            isLoadingLiveData.postValue(false)
        } catch (e: Exception) {
            (errorMessageLiveData as MutableLiveData).postValue(e.message)
        }

        (isLoadingLiveData as MutableLiveData).postValue(false)
    }
}