package com.example.notes

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface QodAPI {
    @GET("/jokes/{random}")
    fun findJoke(@Path("random") random : String) : Call<QoD>
}