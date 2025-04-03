package com.example.di

import com.example.data.db.MarvelRepositoryImpl
import com.example.domain.db.MarvelRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn (SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideMarvelRepository() :
            MarvelRepository = MarvelRepositoryImpl()

}