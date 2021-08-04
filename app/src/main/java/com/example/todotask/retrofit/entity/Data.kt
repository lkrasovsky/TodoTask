package com.example.todotask.retrofit.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "todos")
data class Data(
    @SerializedName("due_on")
    val dueOn: String,
    @PrimaryKey
    val id: Int,
    val status: String,
    val title: String,
    @SerializedName("user_id")
    val userId: Int
)