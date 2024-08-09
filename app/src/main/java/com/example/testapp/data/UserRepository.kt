package com.example.testapp.data

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.testapp.data.api.config.ApiService
import com.example.testapp.data.api.response.DataItem
import com.example.testapp.data.database.UsernameDatabase

class UserRepository private constructor(private val apiService: ApiService, private val usernameDatabase: UsernameDatabase) {
    fun getUsername() : LiveData<PagingData<DataItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5
            ),
            pagingSourceFactory = {
                QuotePagingSource(apiService)
            }
        ).liveData
    }

    companion object {
        @Volatile
        private var instance: UserRepository? = null
        fun getInstance(apiService: ApiService, usernameDatabase: UsernameDatabase) =
            instance ?: synchronized(this) {
                instance ?: UserRepository(apiService, usernameDatabase)
            }.also { instance = it }
    }
}