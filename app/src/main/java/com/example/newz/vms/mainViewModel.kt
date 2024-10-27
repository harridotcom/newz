package com.example.newz.vms

import androidx.lifecycle.ViewModel
import com.example.newz.api.ApiService
import com.example.newz.other.Repository
import com.example.newz.room.FavoriteNews
import com.example.newz.room.NewsDao

class MainViewModel(val repository: Repository): ViewModel() {
    suspend fun getNews() = repository.getNews()

    suspend fun getFavoriteNews(userId: String) = repository.getFavoriteNews(userId)

    suspend fun deleteFavorite(favoriteNews: FavoriteNews) = repository.deleteFavorite(favoriteNews)

    suspend fun insertFavorite(favoriteNews: FavoriteNews) = repository.insertFavorite(favoriteNews)
}