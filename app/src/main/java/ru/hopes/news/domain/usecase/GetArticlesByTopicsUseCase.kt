package ru.hopes.news.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.hopes.news.domain.entity.Article
import ru.hopes.news.domain.repository.NewsRepository
import javax.inject.Inject

class GetArticlesByTopicsUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {

    operator fun invoke(topics: List<String>): Flow<List<Article>> {
        return newsRepository.getArticlesByTopics(topics)
    }
}