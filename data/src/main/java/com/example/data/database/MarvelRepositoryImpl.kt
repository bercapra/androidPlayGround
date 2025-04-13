package com.example.data.database

import com.example.domain.db.MarvelRepository
import com.example.domain.entity.MarvelCharacter
import com.example.domain.utils.CoroutineResult
import javax.inject.Inject

class MarvelRepositoryImpl @Inject constructor() : MarvelRepository {
//class MarvelRepositoryImpl(private val charactersDao: MarvelDao) : MarvelRepository {

    override suspend fun getDBCharacters(): CoroutineResult<List<MarvelCharacter>> =
        CoroutineResult.Success(listOf())
//        charactersDao.getDBCharacters().let {
//            if (it.isNotEmpty()) {
//                CoroutineResult.Success(it.mapToCharacterList())
//            } else {
//                CoroutineResult.Failure(Exception())
//            }
//        }

    override suspend fun insertCharactersToDB(charactersList: List<MarvelCharacter>) {
//        charactersList.forEach {
//            charactersDao.insertCharacter(it.mapToDataBaseCharacter())
//        }
    }
}