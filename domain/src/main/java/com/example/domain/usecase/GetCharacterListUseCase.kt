package com.example.domain.usecase

import com.example.domain.datasource.local.MarvelLocalDatasource
import com.example.domain.datasource.remote.CharacterRemoteDatasource
import com.example.domain.entity.MarvelCharacter
import com.example.domain.utils.CoroutineResult
import javax.inject.Inject

fun interface GetCharacterListUseCase {
    operator fun invoke(): CoroutineResult<List<MarvelCharacter>>
}

class GetCharacterListUseCaseImpl @Inject constructor(
    private val characterService: CharacterRemoteDatasource,
    private val marvelRepository: MarvelLocalDatasource
) : GetCharacterListUseCase {
    override operator fun invoke(): CoroutineResult<List<MarvelCharacter>> {
        return when (val serviceResult = characterService.getCharacterList()) {
            is CoroutineResult.Success -> {
                marvelRepository.insertCharactersToDB(serviceResult.data)
                marvelRepository.getDBCharacters()
            }

            is CoroutineResult.Failure -> marvelRepository.getDBCharacters()
        }
    }
}
