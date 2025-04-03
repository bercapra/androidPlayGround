package com.example.domain.db

import com.example.domain.entity.MarvelCharacter
import com.example.domain.utils.CoroutineResult

interface MarvelRepository {
    suspend fun getDBCharacters(): CoroutineResult<List<MarvelCharacter>>
    suspend fun insertCharactersToDB(charactersList: List<MarvelCharacter>)
}