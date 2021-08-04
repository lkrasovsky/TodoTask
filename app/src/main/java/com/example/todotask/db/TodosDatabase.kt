package com.example.todotask.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todotask.retrofit.entity.Data

@Database(
    entities = [
        Data::class
    ],
    version = 1,
    exportSchema = false
)
abstract class TodosDatabase : RoomDatabase() {
    abstract val todosDao: TodosDao
}