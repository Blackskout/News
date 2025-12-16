package ru.hopes.news.domain.usecase

import ru.hopes.news.domain.entity.Language
import ru.hopes.news.domain.repository.SettingsRepository
import javax.inject.Inject

class UpdateLanguageUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository
) {
    suspend operator fun invoke(language: Language) {
        settingsRepository.updateLanguage(language)
    }
}