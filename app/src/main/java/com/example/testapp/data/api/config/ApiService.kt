package com.example.testapp.data.api.config

import com.example.testapp.data.api.response.UsernameResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService  {
    @GET("users")
    suspend fun getUsername(
        @Query("page") page: Int,
        @Query("per_page") size: Int
    ): UsernameResponse
}