package com.example.newz

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.example.newz.api.RetrofitHelper
import com.example.newz.other.MainViewModelFactory
import com.example.newz.other.Navigation
import com.example.newz.other.Repository
import com.example.newz.room.RoomDb
import com.example.newz.vms.AuthViewModel
import com.example.newz.vms.MainViewModel

class MainActivity : ComponentActivity() {
    private lateinit var authViewModel: AuthViewModel
    private lateinit var mainViewModel: MainViewModel
    private lateinit var repository: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val roomdb = RoomDb.getDatabase(this)
        var newsDao = roomdb.getnewsDao()
        val apiService = RetrofitHelper.getApiService()
        repository = Repository(apiService, newsDao)
        val factory = MainViewModelFactory(repository)

        authViewModel = AuthViewModel()
        mainViewModel = ViewModelProvider(this, factory)[MainViewModel::class.java]
        repository = Repository(apiService, newsDao)

        setContent {
            Navigation(
                authViewModel = authViewModel,
                repository = repository,
                mainViewModel
            )
        }

    }
}