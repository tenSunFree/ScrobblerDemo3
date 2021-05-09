package com.example.scrobblerdemo3.common.di

import android.app.Application
import android.util.Log
import com.chuckerteam.chucker.api.ChuckerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class InterceptorModule {
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    fun provideChuckerInterceptor(application: Application): ChuckerInterceptor =
        ChuckerInterceptor(application)

    @Singleton
    @Provides
    @Named("logging")
    internal fun provideHttpLoggingInterceptor(): Interceptor =
        HttpLoggingInterceptor { message ->
            Log.d("more", message)
        }.setLevel(HttpLoggingInterceptor.Level.BODY)
}