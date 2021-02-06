package com.example.newscroll.Repositories

import androidx.annotation.WorkerThread
import com.example.newscroll.Room.LikeNews
import com.example.newscroll.Room.LikeNewsDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LikeNewsRepository @Inject constructor(
        private val likeNewsDao: LikeNewsDao
) {
    val newsList : Flow<List<LikeNews>> = likeNewsDao.getAllLikeNews()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(news : LikeNews) {
        likeNewsDao.insert(news)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(news : LikeNews) {
        likeNewsDao.delete(news)
    }
}