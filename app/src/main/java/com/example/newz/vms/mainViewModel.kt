package com.example.newz.vms

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newz.api.ApiService
import com.example.newz.api.NewsList
import com.example.newz.other.Repository
import com.example.newz.room.FavoriteNews
import com.example.newz.room.NewsDao

class MainViewModel(val repository: Repository): ViewModel() {

    var mutableLiveDataDb = MutableLiveData<List<FavoriteNews>>()
    var liveDataDb: LiveData<List<FavoriteNews>> = mutableLiveDataDb

    suspend fun getNews() = repository.getNews()

    suspend fun getFavoriteNews(userId: String){
        mutableLiveDataDb.postValue(repository.getFavoriteNews(userId))
    }

    suspend fun deleteFavorite(favoriteNews: FavoriteNews) {
        repository.deleteFavorite(favoriteNews)
        getFavoriteNews(favoriteNews.userId)
    }

    suspend fun insertFavorite(favoriteNews: FavoriteNews) {
        repository.insertFavorite(favoriteNews)
        getFavoriteNews(favoriteNews.userId)
    }
}