package com.example.data

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.data.datasource.local.MarvelDB
import com.example.data.datasource.local.MarvelDao
import com.example.data.datasource.local.entity.CharacterLocalEntity
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue


class MarvelDaoTest {

    private lateinit var db: MarvelDB
    private lateinit var dao: MarvelDao

    @Before
    fun setup() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            MarvelDB::class.java
        ).allowMainThreadQueries().build()

        dao = db.marvelDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @Test
    fun insertCharacter_shouldInsertSuccessfully() {
        val character = CharacterLocalEntity(ID_SPIDER_MAN, NAME_SPIDER_MAN, NAME_SPIDER_MAN, PATH_SPIDER_MAN)

        dao.insertCharacter(character)
        val result = dao.getCharacter(ID_SPIDER_MAN)

        assertEquals(SIZE_EXPECTED, result.size)
        assertEquals(NAME_SPIDER_MAN, result.first().name)
    }

    @Test
    fun getDBCharacters_shouldReturnAllInsertedCharacters() {
        val characters = listOf(
            CharacterLocalEntity(ID_SPIDER_MAN, NAME_SPIDER_MAN, DESCRIPTION_SPIDER_MAN, PATH_SPIDER_MAN),
            CharacterLocalEntity(ID_VISION, NAME_VISION, DESCRIPTION_VISION, PATH_VISION)
        )

        characters.forEach { dao.insertCharacter(it) }

        val result = dao.getDBCharacters()

        assertEquals(SIZE_EXPECTED_LIST, result.size)
        assertTrue(result.any { it.name == NAME_VISION})
    }

    @Test
    fun getCharacter_shouldReturnCorrectCharacterById() {
        val character = CharacterLocalEntity(ID_VISION, NAME_VISION, DESCRIPTION_VISION, PATH_VISION)

        dao.insertCharacter(character)
        val result = dao.getCharacter(ID_VISION)

        assertEquals(SIZE_EXPECTED, result.size)
        assertEquals(NAME_VISION, result.first().name)
    }

    companion object {
        private const val ID_SPIDER_MAN = 1011347
        private const val NAME_SPIDER_MAN = "Spider-Man"
        private const val DESCRIPTION_SPIDER_MAN = "With great power there must also come great responsibility."
        private const val PATH_SPIDER_MAN = "http://terrigen-cdn-dev.marvel.com/content/prod/1x/203ham_com_crd_01"
        private const val ID_VISION = 1017100
        private const val NAME_VISION = "Vision"
        private const val DESCRIPTION_VISION = "Android"
        private const val PATH_VISION = "http://vision.png"
        private const val SIZE_EXPECTED = 1
        private const val SIZE_EXPECTED_LIST = 2
    }
}
