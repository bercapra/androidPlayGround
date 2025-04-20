package com.example.domain.datasource.remote

import com.example.domain.entity.MarvelCharacter
import com.example.domain.utils.CoroutineResult

interface CharacterRemoteDatasource {
    fun getCharacterList(): CoroutineResult<List<MarvelCharacter>>
    fun getCharacter(characterId: Int): CoroutineResult<MarvelCharacter>
}