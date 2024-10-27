package com.example.newz.other

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newz.api.ApiService
import com.example.newz.room.NewsDao
import com.example.newz.vms.MainViewModel

class MainViewModelFactory(val repository: Repository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}