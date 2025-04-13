package com.example.domain.datasource

import com.example.domain.entity.MarvelCharacter
import com.example.domain.utils.CoroutineResult


interface CharacterService {
    fun getCharacterList(): CoroutineResult<List<MarvelCharacter>>
    fun getCharacter(characterId: Int): CoroutineResult<MarvelCharacter>
}