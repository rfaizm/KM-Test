package com.example.testapp.di

import android.content.Context
import com.example.testapp.data.UserRepository
import com.example.testapp.data.api.config.ApiConfig
import com.example.testapp.data.database.UsernameDatabase

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val apiService = ApiConfig.getApiService()
        val database = UsernameDatabase.getDatabase(context)
        return UserRepository.getInstance(apiService, database)
    }
}