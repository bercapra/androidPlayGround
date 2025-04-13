package com.example.domain.usecase

import com.example.domain.datasource.CharacterService
import com.example.domain.db.MarvelRepository
import com.example.domain.entity.MarvelCharacter
import com.example.domain.utils.CoroutineResult
import javax.inject.Inject

interface GetCharacterListUseCase {
    operator fun invoke(): CoroutineResult<List<MarvelCharacter>>
}

class GetCharacterListUseCaseImpl @Inject constructor(
    private val characterService: CharacterService,
    private val marvelRepository: MarvelRepository
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
