package com.example.domain.datasource

import com.example.domain.entity.MarvelCharacter
import com.example.domain.utils.CoroutineResult


interface CharacterService {
    suspend fun getCharacterList(): CoroutineResult<List<MarvelCharacter>>
}