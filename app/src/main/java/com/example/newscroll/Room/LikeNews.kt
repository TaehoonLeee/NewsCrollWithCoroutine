package com.example.newscroll.Room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "like_news")
data class LikeNews(
    @PrimaryKey(autoGenerate = true)
    val id : Long?,
    val platForm : String,
    val thumbnailUrl : String,
    val title : String,
    val description : String,
    val url : String?
)