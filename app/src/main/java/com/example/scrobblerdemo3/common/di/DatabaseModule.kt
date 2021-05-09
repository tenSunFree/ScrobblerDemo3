package com.example.scrobblerdemo3.common.di

import android.app.Application
import com.example.scrobblerdemo3.common.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Suppress("TooManyFunctions")
@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(application: Application) =
        com.example.scrobblerdemo3.common.local.provideDatabase(application)

    @Provides
    @Singleton
    fun provideHomeDao(database: AppDatabase) = database.homeDao()
}