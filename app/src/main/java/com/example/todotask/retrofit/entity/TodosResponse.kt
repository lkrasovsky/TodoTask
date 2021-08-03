package com.example.todotask.retrofit.entity

import com.google.gson.annotations.SerializedName

data class TodosResponse(
    val code: Int,
    @SerializedName("data")
    val todos: List<Data>,
    val meta: Meta
)