package ru.hopes.news.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import ru.hopes.news.data.remote.NewsApiService
import ru.hopes.news.data.repository.NewsRepositoryImpl
import ru.hopes.news.presentation.screen.subscriptions.SubscriptionsScreen
import ru.hopes.news.presentation.ui.theme.NewsTheme
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var repository: NewsRepositoryImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        lifecycleScope.launch {
            repository.addSubscription("Kotlin")
            repository.updateArticlesForTopic("Kotlin   ")
        }
        setContent {
            NewsTheme {
                SubscriptionsScreen(
                    onNavigateToSettings = {}
                )
            }
        }
    }
}