package com.example.data.mapper

import com.example.data.datasource.remote.model.CharacterResponse
import com.example.data.datasource.remote.model.ImgResponse
import com.example.domain.entity.MarvelCharacter
import org.junit.Assert.assertEquals
import org.junit.Test

class CharacterMapperTest {

    @Test
    fun `mapToLocalCharacter should correctly map all fields`() {
        val characterResponse = CharacterResponse(
            id = ID,
            name = NAME,
            description = DESCRIPTION,
            img = ImgResponse(path = IMG, ext = EXT)
        )

        val result: MarvelCharacter = characterResponse.mapToLocalCharacter()

        assertEquals(ID, result.id)
        assertEquals(NAME, result.name)
        assertEquals(DESCRIPTION, result.description)
        assertEquals(IMG+POINT+EXT, result.img)
    }

    @Test
    fun `mapToLocalCharacter should handle empty description and image`() {
        val characterResponse = CharacterResponse(
            id = ID_UNKNOWN,
            name = NAME_UNKNOWN,
            description = EMPTY,
            img = ImgResponse(path = EMPTY, ext = EMPTY)
        )

        val result: MarvelCharacter = characterResponse.mapToLocalCharacter()

        assertEquals(ID_UNKNOWN, result.id)
        assertEquals(NAME_UNKNOWN, result.name)
        assertEquals(EMPTY, result.description)
        assertEquals(POINT, result.img) // path + "." + ext → "." cuando ambos son vacíos
    }

    companion object {
        private const val ID = 1011347
        private const val NAME = "Spider-Man"
        private const val DESCRIPTION =
            "With great power there must also come great responsibility."
        private const val IMG =
            "http://terrigen-cdn-dev.marvel.com/content/prod/1x/203ham_com_crd_01"
        private const val EXT = "jpg"
        private const val ID_UNKNOWN = 102
        private const val NAME_UNKNOWN = "Unknown Hero"
        private const val EMPTY = ""
        private const val POINT = "."
    }
}