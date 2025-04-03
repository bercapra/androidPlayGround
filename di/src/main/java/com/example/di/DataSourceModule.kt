package com.example.di

import com.example.data.dataSource.CharacterServiceImpl
import com.example.domain.datasource.CharacterService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Provides
    fun provideCharacterService() :
            CharacterService = CharacterServiceImpl()

}