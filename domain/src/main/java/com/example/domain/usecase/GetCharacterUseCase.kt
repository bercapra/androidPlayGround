package com.example.domain.usecase

import com.example.domain.datasource.local.MarvelLocalDatasource
import com.example.domain.datasource.remote.CharacterRemoteDatasource
import com.example.domain.entity.MarvelCharacter
import com.example.domain.utils.CoroutineResult
import javax.inject.Inject

fun interface GetCharacterUseCase {
    operator fun invoke(characterId: Int): CoroutineResult<MarvelCharacter>
}

class GetCharacterUseCaseImpl @Inject constructor(
    private val characterService: CharacterRemoteDatasource,
    private val db: MarvelLocalDatasource
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
