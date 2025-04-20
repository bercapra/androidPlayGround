package com.example.data.datasource.local

import com.example.data.datasource.local.entity.CharacterLocalEntity
import com.example.domain.entity.MarvelCharacter
import com.example.domain.utils.CoroutineResult
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class MarvelLocalDatasourceImplTest {

    private lateinit var marvelDao: MarvelDao
    private lateinit var repository: MarvelLocalDatasourceImpl

    @Before
    fun setUp() {
        marvelDao = mockk(relaxed = true)
        repository = MarvelLocalDatasourceImpl(marvelDao)
    }

    @Test
    fun `getDBCharacters returns Success when dao returns data`() {
        val localCharacters = listOf(mockk<CharacterLocalEntity>(relaxed = true))
        every { marvelDao.getDBCharacters() } returns localCharacters

        val result = repository.getDBCharacters()

        assertTrue(result is CoroutineResult.Success)
        verify { marvelDao.getDBCharacters() }
    }

    @Test
    fun `getDBCharacters returns Failure when dao returns empty list`() {
        every { marvelDao.getDBCharacters() } returns emptyList()

        val result = repository.getDBCharacters()

        assertTrue(result is CoroutineResult.Failure)
    }

    @Test
    fun `insertCharactersToDB calls insertCharacter for each item`() {
        val charactersList = listOf(
            mockk<MarvelCharacter>(relaxed = true)
        )
        every { marvelDao.insertCharacter(any()) } just Runs

        repository.insertCharactersToDB(charactersList)

        verify(exactly = charactersList.size) { marvelDao.insertCharacter(any()) }
    }

    @Test
    fun `getCharacter returns Success when dao returns character`() {
        val localCharacter = mockk<CharacterLocalEntity>(relaxed = true)
        every { marvelDao.getCharacter(any()) } returns listOf(localCharacter)

        val result = repository.getCharacter(CHARACTER_ID)

        assertTrue(result is CoroutineResult.Success)
        verify { marvelDao.getCharacter(CHARACTER_ID) }
    }

    @Test
    fun `getCharacter returns Failure when dao returns empty list`() {
        every { marvelDao.getCharacter(any()) } returns emptyList()

        val result = repository.getCharacter(CHARACTER_ID)

        assertTrue(result is CoroutineResult.Failure)
    }

    @Test
    fun `insertCharacterToDB calls insertCharacter with mapped object`() {
        val character = mockk<MarvelCharacter>(relaxed = true)
        every { marvelDao.insertCharacter(any()) } just Runs

        repository.insertCharacterToDB(character)

        verify { marvelDao.insertCharacter(any()) }
    }

    companion object {
        private const val CHARACTER_ID = 1011347
    }
}
