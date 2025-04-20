package com.example.data.datasource.remote

import com.example.data.mapper.mapToLocalCharacter
import com.example.data.mapper.mapToLocalCharacterList
import com.example.data.datasource.remote.api.MarvelApi
import com.example.domain.datasource.remote.CharacterRemoteDatasource
import com.example.domain.entity.MarvelCharacter
import com.example.domain.utils.CoroutineResult
import javax.inject.Inject

class CharacterRemoteImpl @Inject constructor(private val marvelApi: MarvelApi) : CharacterRemoteDatasource {

    override fun getCharacterList(): CoroutineResult<List<MarvelCharacter>> {
        try {
            val callResponse = marvelApi.getCharacterList()
            val response = callResponse.execute()
            if (response.isSuccessful) response.body()?.let {
                return CoroutineResult.Success(it.mapToLocalCharacterList())
            }
        } catch (e: Exception) {
            return CoroutineResult.Failure(Exception())
        }
        return CoroutineResult.Failure(Exception())
    }

    override fun getCharacter(characterId: Int): CoroutineResult<MarvelCharacter> {
        try {
            val callResponse = marvelApi.getCharacter(characterId)
            val response = callResponse.execute()
            if (response.isSuccessful) response.body()?.let {
                return CoroutineResult.Success(it.mapToLocalCharacter())
            }
        } catch (e: Exception) {
            return CoroutineResult.Failure(Exception())
        }
        return CoroutineResult.Failure(Exception())
    }
}