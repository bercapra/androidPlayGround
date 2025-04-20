package com.example.domain.usecase

import com.example.domain.datasource.local.MarvelLocalDatasource
import com.example.domain.datasource.remote.CharacterRemoteDatasource
import com.example.domain.entity.MarvelCharacter
import com.example.domain.utils.CoroutineResult
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetCharacterListUseCaseTest {

    @MockK
    private lateinit var characterService: CharacterRemoteDatasource

    @MockK
    private lateinit var marvelRepository: MarvelLocalDatasource

    @MockK
    private lateinit var characterList: List<MarvelCharacter>

    private lateinit var getCharacterListUseCase: GetCharacterListUseCase

    @Before
    fun init() {
        MockKAnnotations.init(this, relaxUnitFun = true)

        getCharacterListUseCase = GetCharacterListUseCaseImpl(characterService, marvelRepository)
    }

    @Test
    fun `if use case returns success`() {
        every { characterService.getCharacterList() } returns CoroutineResult.Success(characterList)
        every { marvelRepository.getDBCharacters() } returns CoroutineResult.Success(characterList)

        val result = getCharacterListUseCase()

        verify { marvelRepository.insertCharactersToDB(characterList) }
        verify { marvelRepository.getDBCharacters() }

        assertEquals(characterList, (result as CoroutineResult.Success).data)
    }

    @Test
    fun `if use case returns failure - empty DB`() {
        every { characterService.getCharacterList() } returns CoroutineResult.Failure(Exception(MSG))
        every { marvelRepository.getDBCharacters() } returns CoroutineResult.Failure(Exception(MSG))

        val result = getCharacterListUseCase()

        verify { marvelRepository.getDBCharacters() }

        assertEquals(MSG, (result as CoroutineResult.Failure).exception.message)
    }

    @Test
    fun `if use case returns failure - !empty DB`() {
        every { characterService.getCharacterList() } returns CoroutineResult.Failure(Exception(MSG))
        every { marvelRepository.getDBCharacters() } returns CoroutineResult.Success(characterList)

        val result = getCharacterListUseCase()

        verify { marvelRepository.getDBCharacters() }

        assertEquals(characterList, (result as CoroutineResult.Success).data)
    }

    companion object {
        private const val MSG = "ERROR"
    }
}