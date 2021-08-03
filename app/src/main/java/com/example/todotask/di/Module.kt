package com.example.todotask.di

import com.example.todotask.retrofit.TodoRepository
import com.example.todotask.retrofit.TodoService
import org.koin.dsl.module

val module = module {
    single { TodoService() }
    single { TodoRepository(get()) }
}