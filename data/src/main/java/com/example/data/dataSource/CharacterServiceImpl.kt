package com.example.data.dataSource

import com.example.domain.datasource.CharacterService
import com.example.domain.entity.MarvelCharacter
import com.example.domain.utils.CoroutineResult
import javax.inject.Inject

class CharacterServiceImpl @Inject constructor() : CharacterService {
//class CharacterServiceImpl @Inject constructor(private val marvelApi: MarvelApi) : CharacterService {

    override suspend fun getCharacterList(): CoroutineResult<List<MarvelCharacter>> {
        return CoroutineResult.Success(listOf())
//        try {
//            val callResponse = marvelApi.getCharacterList()
//            val response = callResponse.execute()
//            if (response.isSuccessful) response.body()?.let {
//                return CoroutineResult.Success(it.mapToLocalCharacterList())
//            }
//        } catch (e: Exception) {
//            return CoroutineResult.Failure(Exception())
//        }
//        return CoroutineResult.Failure(Exception())
    }
}

