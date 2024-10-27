package com.example.newz.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavoriteNews(news: FavoriteNews)

    @Delete
    suspend fun deleteFavNews(news: FavoriteNews)

    @Query("SELECT * FROM favorite_news WHERE userId = :userId")
    suspend fun getFavoriteNews(userId: String): List<FavoriteNews> // Change void to List<Result_>
}
