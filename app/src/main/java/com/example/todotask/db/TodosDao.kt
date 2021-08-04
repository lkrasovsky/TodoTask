package com.example.todotask.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.todotask.retrofit.entity.Data

@Dao
interface TodosDao {

    @Query("SELECT * FROM todos WHERE id = :id")
    fun getTodoById(id: Int): Data

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveTodos(todos: List<Data>)
}