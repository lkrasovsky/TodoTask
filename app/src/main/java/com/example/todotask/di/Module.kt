package com.example.todotask.di

import com.example.todotask.retrofit.TodoRepository
import com.example.todotask.retrofit.TodoService
import com.example.todotask.ui.screens.list.ListViewModel
import org.koin.dsl.module

val module = module {
    // Retrofit
    single { TodoService() }

    // Repository
    single { TodoRepository(get()) }

    // ViewModel
    single { ListViewModel(get()) }
}