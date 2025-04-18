package com.example.data.datasource.local

import com.example.data.datasource.local.entity.CharacterLocalEntity
import io.mockk.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals

class MarvelDBTest {

    private lateinit var mockDB: MarvelDB
    private lateinit var mockDao: MarvelDao

    private val dummyCharacter = CharacterLocalEntity(
        id = ID_HULK,
        name = NAME_HULK,
        description = DESCRIPTION_HULK,
        img = PATH_HULK
    )

    @Before
    fun setUp() {
        mockDao = mockk()
        mockDB = mockk()

        every { mockDB.marvelDao() } returns mockDao

        every { mockDao.insertCharacter(any()) } just Runs
        every { mockDao.getCharacter(ID_HULK) } returns listOf(dummyCharacter)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `should return marvelDao from DB`() {
        val dao = mockDB.marvelDao()

        assertEquals(mockDao, dao)
        verify(exactly = 1) { mockDB.marvelDao() }
    }

    @Test
    fun `should insert and get character via dao`() {
        mockDao.insertCharacter(dummyCharacter)
        val result = mockDao.getCharacter(ID_HULK)

        assertEquals(RESULT_SIZE, result.size)
        assertEquals(NAME_HULK, result.first().name)

        verify { mockDao.insertCharacter(dummyCharacter) }
        verify { mockDao.getCharacter(ID_HULK) }
    }

    companion object {
        private const val ID_HULK = 1011347
        private const val NAME_HULK = "Hulk"
        private const val DESCRIPTION_HULK = "Strongest Avenger"
        private const val PATH_HULK = "http://hulk.png"
        private const val RESULT_SIZE = 1
    }
}
