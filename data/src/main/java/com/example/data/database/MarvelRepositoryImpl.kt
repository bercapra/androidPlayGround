package com.example.data.database

import com.example.data.mapper.mapToCharacterList
import com.example.data.mapper.mapToDataBaseCharacter
import com.example.data.mapper.mapToLocalCharacter
import com.example.domain.db.MarvelRepository
import com.example.domain.entity.MarvelCharacter
import com.example.domain.utils.CoroutineResult

class MarvelRepositoryImpl(private val charactersDao: MarvelDao) : MarvelRepository {

    override fun getDBCharacters(): CoroutineResult<List<MarvelCharacter>> =
        charactersDao.getDBCharacters().let {
            if (it.isNotEmpty()) {
                CoroutineResult.Success(it.mapToCharacterList())
            } else {
                CoroutineResult.Failure(Exception())
            }
        }

    override fun insertCharactersToDB(charactersList: List<MarvelCharacter>) {
        charactersList.forEach {
            charactersDao.insertCharacter(it.mapToDataBaseCharacter())
        }
    }

    override fun getCharacter(characterId: Int): CoroutineResult<MarvelCharacter> =
        charactersDao.getCharacter(characterId).let {
            if (it.isNotEmpty()) {
                CoroutineResult.Success(it.first().mapToLocalCharacter())
            } else {
                CoroutineResult.Failure(Exception())
            }
        }

    override fun insertCharacterToDB(character: MarvelCharacter) {
        charactersDao.insertCharacter(character.mapToDataBaseCharacter())
    }
}
