package com.example.newz.other

import com.example.newz.api.ApiService
import com.example.newz.room.FavoriteNews
import com.example.newz.room.NewsDao
import com.example.newz.vms.MainViewModel

class Repository(val apiService: ApiService, val newsDao: NewsDao) {

    suspend fun getNews() = apiService.getNews()

    suspend fun getFavoriteNews(userId: String) = newsDao.getFavoriteNews(userId)

    suspend fun deleteFavorite(favoriteNews: FavoriteNews) = newsDao.deleteFavNews(favoriteNews)

    suspend fun insertFavorite(favoriteNews: FavoriteNews) = newsDao.insertFavoriteNews(favoriteNews)
}