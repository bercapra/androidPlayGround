package com.example.di

import com.example.domain.usecase.GetCharacterListUseCase
import com.example.domain.usecase.GetCharacterListUseCaseImpl
import com.example.domain.datasource.remote.CharacterRemoteDatasource
import com.example.domain.datasource.local.MarvelLocalDatasource
import com.example.domain.usecase.GetCharacterUseCase
import com.example.domain.usecase.GetCharacterUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    fun provideGetCharacterListUseCase(characterService: CharacterRemoteDatasource, marvelRepository: MarvelLocalDatasource): GetCharacterListUseCase =
        GetCharacterListUseCaseImpl(characterService, marvelRepository)

    @Provides
    fun provideGetCharacterUseCase(characterService: CharacterRemoteDatasource, marvelRepository: MarvelLocalDatasource): GetCharacterUseCase =
        GetCharacterUseCaseImpl(characterService, marvelRepository)
}
