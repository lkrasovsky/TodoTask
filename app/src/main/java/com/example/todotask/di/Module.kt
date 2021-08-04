package com.example.todotask.di

import androidx.room.Room
import com.example.todotask.db.TodosDatabase
import com.example.todotask.retrofit.TodoRepository
import com.example.todotask.retrofit.TodoService
import com.example.todotask.ui.screens.list.ListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val module = module {
    // Retrofit
    single { TodoService() }

    // Repository
    single { TodoRepository(get(), get()) }

    // Room
    single {
        return@single Room.databaseBuilder(
            androidContext(), TodosDatabase::class.java, "todos_database"
        ).build()
    }

    // ViewModel
    single { ListViewModel(get()) }
}