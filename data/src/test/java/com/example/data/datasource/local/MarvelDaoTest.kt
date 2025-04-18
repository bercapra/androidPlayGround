package com.example.data.datasource.local

import com.example.data.datasource.local.entity.CharacterLocalEntity
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

class DummyRepositoryTest {

    private lateinit var dao: MarvelDao
    private lateinit var repository: DummyRepository

    @Before
    fun setUp() {
        dao = mockk()
        repository = DummyRepository(dao)
    }

    @Test
    fun `getAllCharacters should return characters from DAO`() {
        val fakeList = listOf(
            CharacterLocalEntity(ID_HULK, NAME_HULK, DESCRIPTION_HULK, PATH_HULK),
            CharacterLocalEntity(ID_LOKI, NAME_LOKI, DESCRIPTION_LOKI, DESCRIPTION_LOKI)
        )
        every { dao.getDBCharacters() } returns fakeList

        val result = repository.getAllCharacters()

        assertEquals(RESULT_SIZE, result.size)
        assertEquals(NAME_HULK, result.first().name)
        verify { dao.getDBCharacters() }
    }

    @Test
    fun `getCharacterById should return character if found`() {
        val character = CharacterLocalEntity(ID_LOKI, NAME_LOKI, DESCRIPTION_LOKI, PATH_LOKI)
        every { dao.getCharacter(ID_LOKI) } returns listOf(character)

        val result = repository.getCharacterById(ID_LOKI)

        assertEquals(NAME_LOKI, result?.name)
        verify { dao.getCharacter(ID_LOKI) }
    }

    @Test
    fun `getCharacterById should return null if not found`() {
        every { dao.getCharacter(ID_CAP_AME) } returns emptyList()

        val result = repository.getCharacterById(ID_CAP_AME)

        assertNull(result)
        verify { dao.getCharacter(ID_CAP_AME) }
    }

    @Test
    fun `saveCharacter should call insertCharacter on DAO`() {
        val character = CharacterLocalEntity(ID_HULK, NAME_HULK, DESCRIPTION_HULK, PATH_HULK)
        every { dao.insertCharacter(character) } just Runs

        repository.saveCharacter(character)

        verify { dao.insertCharacter(character) }
    }

    companion object {
        private const val ID_CAP_AME = 1011334
        private const val ID_HULK = 1011347
        private const val NAME_HULK = "Hulk"
        private const val DESCRIPTION_HULK = "Strongest Avenger"
        private const val PATH_HULK = "http://hulk.png"
        private const val ID_LOKI = 1017100
        private const val NAME_LOKI = "Loki"
        private const val DESCRIPTION_LOKI = "Trickster"
        private const val PATH_LOKI = "http://loki.png"
        private const val RESULT_SIZE = 2
    }
}
