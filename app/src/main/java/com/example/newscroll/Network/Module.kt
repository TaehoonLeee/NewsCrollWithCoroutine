package com.example.newscroll.Network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object Module {
    @Singleton
    @Provides
    fun provideCrollingService() : CrollingService{
        return CrollingService()
    }
}