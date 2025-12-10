package ru.hopes.news.domain.usecase

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import ru.hopes.news.domain.repository.NewsRepository
import javax.inject.Inject
import kotlin.coroutines.coroutineContext

class AddSubscriptionUseCase @Inject constructor(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(topic: String) {
        newsRepository.addSubscription(topic)
        CoroutineScope(coroutineContext).launch {
            newsRepository.updateArticlesForTopic(topic)
        }
    }
}