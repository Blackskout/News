package ru.hopes.news.data.background

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import ru.hopes.news.domain.usecase.UpdateSubscribedArticlesUseCase

@HiltWorker
class RefreshDataWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParameters: WorkerParameters,
    private val updateSubscribedArticlesUseCase: UpdateSubscribedArticlesUseCase
): CoroutineWorker(context, workerParameters) {
    override suspend fun doWork(): Result {
        Log.d("Rikarti", "Start")
        updateSubscribedArticlesUseCase()
        Log.d("Rikarti", "Finish")
        return Result.success()
    }
}