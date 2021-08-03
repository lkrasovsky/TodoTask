package com.example.todotask.retrofit.entity

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("due_on")
    val dueOn: String,
    val id: Int,
    val status: String,
    val title: String,
    @SerializedName("user_id")
    val userId: Int
)