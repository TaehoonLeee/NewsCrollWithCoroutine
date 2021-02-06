package com.example.newscroll.Room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LikeNews::class], version = 1)
abstract class LikeDatabase : RoomDatabase() {
    abstract fun LikeNewsDao() : LikeNewsDao
}