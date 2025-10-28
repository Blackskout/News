package ru.hopes.news.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("v2/everything?apiKey=45334060b8cd475e82f4214fcab4559c")
    suspend fun loadArticles(
        @Query("q") topic: String
    ): NewsResponseDto
}