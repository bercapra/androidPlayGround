package com.example.di

import com.example.domain.usecase.GetCharacterListUseCase
import com.example.domain.usecase.GetCharacterListUseCaseImpl
import com.example.domain.datasource.CharacterService
import com.example.domain.db.MarvelRepository
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
    fun provideGetCharacterListUseCase(characterService: CharacterService, marvelRepository: MarvelRepository): GetCharacterListUseCase =
        GetCharacterListUseCaseImpl(characterService, marvelRepository)

    @Provides
    fun provideGetCharacterUseCase(characterService: CharacterService, marvelRepository: MarvelRepository): GetCharacterUseCase =
        GetCharacterUseCaseImpl(characterService, marvelRepository)
}
