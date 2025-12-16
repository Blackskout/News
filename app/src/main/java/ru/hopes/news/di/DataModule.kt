package ru.hopes.news.di

import android.content.Context
import androidx.room.Room
import androidx.work.WorkManager
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.create
import ru.hopes.news.data.local.NewsDao
import ru.hopes.news.data.local.NewsDataBase
import ru.hopes.news.data.remote.NewsApiService
import ru.hopes.news.data.repository.NewsRepositoryImpl
import ru.hopes.news.data.repository.SettingsRepositoryImpl
import ru.hopes.news.domain.repository.NewsRepository
import ru.hopes.news.domain.repository.SettingsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    @Singleton
    fun bindsSettingsRepository(
        impl: SettingsRepositoryImpl
    ): SettingsRepository

    @Binds
    @Singleton
    fun bindsNewsRepository(
        impl: NewsRepositoryImpl
    ): NewsRepository

    companion object {

        @Provides
        @Singleton
        fun provideWorkManager(
            @ApplicationContext context: Context
        ): WorkManager {
            return WorkManager.getInstance(context)
        }

        @Provides
        @Singleton
        fun provideJson(): Json {
            return Json {
                ignoreUnknownKeys = true
                coerceInputValues = true
            }
        }

        @Provides
        @Singleton
        fun provideConverterFactory(
            json: Json
        ): Converter.Factory {
            return json.asConverterFactory("application/json".toMediaType())
        }

        @Provides
        @Singleton
        fun provideRetrofit(
            converterFactory: Converter.Factory
        ): Retrofit {
            return Retrofit.Builder()
                .baseUrl("https://newsapi.org/")
                .addConverterFactory(converterFactory)
                .build()
        }

        @Provides
        @Singleton
        fun provideApiService(
            retrofit: Retrofit
        ): NewsApiService {
            return retrofit.create()
        }

        @Singleton
        @Provides
        fun provideNewsDatabase(
            @ApplicationContext context: Context,
        ): NewsDataBase {
            return Room.databaseBuilder(
                context = context,
                klass = NewsDataBase::class.java,
                name = "news.db"
            ).fallbackToDestructiveMigration(dropAllTables = true).build()
        }

        @Singleton
        @Provides
        fun provideNewsDao(
            database: NewsDataBase
        ): NewsDao {
            return database.newsDao()
        }
    }
}