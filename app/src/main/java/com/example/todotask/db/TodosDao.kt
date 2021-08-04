package com.example.todotask.db

import androidx.room.Query
import com.example.todotask.retrofit.entity.Data

interface TodosDao {

    @Query("SELECT * FROM todos ORDER BY id")
    fun getTodos(): List<Data>

    @Query("SELECT * FROM todos WHERE id = :id")
    fun getTodoById(id: Int): Data
}