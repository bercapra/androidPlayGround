package com.example.domain.datasource.local

import com.example.domain.entity.MarvelCharacter
import com.example.domain.utils.CoroutineResult

interface MarvelLocalDatasource {
    fun getDBCharacters(): CoroutineResult<List<MarvelCharacter>>
    fun getCharacter(characterId: Int): CoroutineResult<MarvelCharacter>
    fun insertCharactersToDB(charactersList: List<MarvelCharacter>)
    fun insertCharacterToDB(character: MarvelCharacter)
}

