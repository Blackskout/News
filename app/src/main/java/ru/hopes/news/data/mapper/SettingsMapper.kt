package ru.hopes.news.data.mapper

import ru.hopes.news.domain.entity.RefreshConfig
import ru.hopes.news.domain.entity.Settings

fun Settings.toRefreshConfig(): RefreshConfig {
    return RefreshConfig(
        language = language,
        interval = interval,
        wifiOnly = wifiOnly
    )
}