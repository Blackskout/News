package ru.hopes.news.domain.usecase

import kotlinx.coroutines.flow.first
import ru.hopes.news.domain.repository.NewsRepository
import ru.hopes.news.domain.repository.SettingsRepository
import javax.inject.Inject

class UpdateSubscribedArticlesUseCase @Inject constructor(
    private val newsRepository: NewsRepository,
    private val settingsRepository: SettingsRepository,
) {

    suspend operator fun invoke(): List<String> {
        val settings = settingsRepository.getSettings().first()
        return newsRepository.updateArticlesForAllSubscriptions(settings.language)
    }
}