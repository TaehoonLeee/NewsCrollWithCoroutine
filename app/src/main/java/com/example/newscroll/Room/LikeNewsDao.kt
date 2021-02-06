package com.example.newscroll.Room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LikeNewsDao {
    @Insert
    fun insert(news : LikeNews)

    @Delete
    fun delete(news : LikeNews)

    @Query("SELECT * FROM like_news")
    fun getAllLikeNews() : Flow<List<LikeNews>>
}