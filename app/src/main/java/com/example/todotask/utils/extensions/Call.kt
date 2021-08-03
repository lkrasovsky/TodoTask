package com.example.todotask.utils.extensions

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun <T> Call<T>.invoke(
    onResponseSuccessful: (response: Response<T>) -> Unit = { _ -> },
    onResponseNotSuccessful: () -> Unit = { },
    onFailure: () -> Unit = {},
    anyway: () -> Unit = {}
) {
    enqueue(object : Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) {
            if (response.isSuccessful) {
                onResponseSuccessful(response)
            } else {
                onResponseNotSuccessful()
            }
            anyway()
        }

        override fun onFailure(call: Call<T>, t: Throwable) {
            onFailure()
            anyway()
        }
    })
}