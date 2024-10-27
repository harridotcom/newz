package com.example.newz.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("latest")
    suspend fun getNews(
        @Query("apikey") apiKey: String = "pub_5733378fb136ff32ed5df03aaa03bcea2475d",
        @Query("category") category: String = "politics",
        @Query("country") country: String = "in"
    ): Response<NewsList>
}