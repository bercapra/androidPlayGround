package com.example.di

import com.example.data.datasource.remote.CharacterRemoteImpl
import com.example.data.datasource.remote.api.MarvelApi
import com.example.domain.datasource.CharacterService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ServiceModule {

    @Provides
    fun provideCharacterService(marvelApi: MarvelApi): CharacterService = CharacterRemoteImpl(marvelApi)
}