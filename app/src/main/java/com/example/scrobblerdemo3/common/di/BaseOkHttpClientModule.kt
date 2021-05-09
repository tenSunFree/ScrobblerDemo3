package com.example.scrobblerdemo3.common.di

import com.example.scrobblerdemo3.common.remote.BaseOkHttpClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient

@Module
@InstallIn(SingletonComponent::class)
class BaseOkHttpClientModule {
    @BaseOkHttpClient
    @Provides
    fun providesBaseOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .build()
}