package com.example.newz.api

data class NewsList(
    val nextPage: String,
    val results: List<Result>,
    val status: String,
    val totalResults: Int
)