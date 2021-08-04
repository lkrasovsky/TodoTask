package com.example.todotask.db

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.todotask.retrofit.entity.Data
import com.example.todotask.retrofit.entity.TodosResponse

interface TodosDao {

    @Query("SELECT * FROM todos WHERE id = :id")
    fun getTodoById(id: Int): TodosResponse

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveTodos(todos: List<Data>)
}