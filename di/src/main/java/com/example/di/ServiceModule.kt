package com.example.di

import com.example.data.service.CharacterServiceImpl
import com.example.data.service.api.MarvelApi
import com.example.domain.datasource.CharacterService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ServiceModule {

    @Provides
    fun provideCharacterService(marvelApi: MarvelApi): CharacterService = CharacterServiceImpl(marvelApi)
}