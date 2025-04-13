package com.example.domain.usecase

import com.example.domain.datasource.CharacterService
import com.example.domain.db.MarvelRepository
import com.example.domain.entity.MarvelCharacter
import com.example.domain.utils.CoroutineResult
import javax.inject.Inject

interface GetCharacterUseCase {
    operator fun invoke(characterId: Int): CoroutineResult<MarvelCharacter>
}

class GetCharacterUseCaseImpl @Inject constructor(
    private val characterService: CharacterService,
    private val db: MarvelRepository
) : GetCharacterUseCase {
    override operator fun invoke(characterId: Int): CoroutineResult<MarvelCharacter> {
        return when (val serviceResult = characterService.getCharacter(characterId)) {
            is CoroutineResult.Success -> {
                db.insertCharacterToDB(serviceResult.data)
                db.getCharacter(serviceResult.data.id)
            }
            is CoroutineResult.Failure -> db.getCharacter(characterId)
        }
    }
}
