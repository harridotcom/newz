package com.example.newz.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_news")
data class FavoriteNews(
    @PrimaryKey
    val article_id: String,
    val userId: String,
    val ai_org: String,
    val ai_region: String,
    val ai_tag: String,
    val category: String,
    val content: String,
    val country: String,
    val creator: String,
    val description: String,
    val duplicate: Boolean,
    val image_url: String,
    val keywords: String,
    val language: String,
    val link: String,
    val pubDate: String,
    val pubDateTZ: String,
    val sentiment: String,
    val sentiment_stats: String,
    val source_icon: String,
    val source_id: String,
    val source_name: String,
    val source_priority: Int,
    val source_url: String,
    val title: String,
    val video_url: String
)