package ru.hopes.news.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.hopes.news.domain.entity.Article
import ru.hopes.news.domain.entity.Language
import ru.hopes.news.domain.entity.RefreshConfig

interface NewsRepository {

    fun getAllSubscriptions(): Flow<List<String>>

    fun startBackgroundRefresh(refreshConfig: RefreshConfig)

    suspend fun addSubscription(topic: String)

    suspend fun updateArticlesForTopic(topic: String, language: Language): Boolean

    suspend fun removeSubscription(topic: String)

    suspend fun updateArticlesForAllSubscriptions(language: Language): List<String>

    fun getArticlesByTopics(topics: List<String>): Flow<List<Article>>

    suspend fun clearAllArticles(topics: List<String>)
}