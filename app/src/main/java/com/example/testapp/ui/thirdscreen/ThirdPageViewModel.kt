package com.example.testapp.ui.thirdscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.testapp.data.UserRepository
import com.example.testapp.data.api.response.DataItem

class ThirdPageViewModel(private val repository: UserRepository) : ViewModel() {
    val quote: LiveData<PagingData<DataItem>> =
        repository.getUsername().cachedIn(viewModelScope)

}