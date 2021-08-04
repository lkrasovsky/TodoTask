package com.example.todotask.di

import androidx.room.Room
import com.example.todotask.db.TodosDatabase
import com.example.todotask.retrofit.TodoRepository
import com.example.todotask.retrofit.TodoService
import com.example.todotask.ui.screens.details.DetailsViewModel
import com.example.todotask.ui.screens.list.ListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val module = module {
    // Retrofit
    single { TodoService(androidContext()) }

    // Repository
    single { TodoRepository(get(), get()) }

    // Room
    single {
        return@single Room.databaseBuilder(
            androidContext(), TodosDatabase::class.java, "todos_database"
        ).build().todosDao
    }

    // ViewModel
    single { ListViewModel(get()) }
    single { DetailsViewModel(get()) }
}