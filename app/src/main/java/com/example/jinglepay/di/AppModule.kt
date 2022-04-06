package com.example.jinglepay.di

import android.app.Activity
import android.content.Context
import com.example.jinglepay.data.network.JinglePayApis
import com.example.jinglepay.data.network.NetworkConnectionInterceptor
import com.example.jinglepay.utils.NavigationWrapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideNetworkConnectionInterceptor(@ApplicationContext context: Context): NetworkConnectionInterceptor {
        return NetworkConnectionInterceptor(context)
    }

    @Provides
    fun provideAuthApi(networkConnectionInterceptor: NetworkConnectionInterceptor): JinglePayApis {
        return JinglePayApis(networkConnectionInterceptor)
    }
}