package com.example.domain.db

import com.example.domain.entity.MarvelCharacter
import com.example.domain.utils.CoroutineResult

interface MarvelRepository {
    fun getDBCharacters(): CoroutineResult<List<MarvelCharacter>>
    fun getCharacter(characterId: Int): CoroutineResult<MarvelCharacter>
    fun insertCharactersToDB(charactersList: List<MarvelCharacter>)
    fun insertCharacterToDB(character: MarvelCharacter)
}

