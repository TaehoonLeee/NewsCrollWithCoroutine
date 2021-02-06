package com.example.newscroll.Network

import android.content.Context
import androidx.room.Room
import com.example.newscroll.Room.LikeDatabase
import com.example.newscroll.Room.LikeNewsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object Module {
    @Singleton
    @Provides
    fun provideCrollingService() : CrollingService{
        return CrollingService()
    }

    @Singleton
    @Provides
    fun provideLikeNewsDao(likeDatabase: LikeDatabase) : LikeNewsDao {
        return likeDatabase.LikeNewsDao()
    }

    @Singleton
    @Provides
    fun provideLikeDatabase(@ApplicationContext app : Context) = Room.databaseBuilder(
        app,
        LikeDatabase::class.java,
        "like_database"
    ).build()
}