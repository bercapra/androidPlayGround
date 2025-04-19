package com.example.data.datasource.remote

import com.example.data.datasource.remote.api.MarvelApi
import com.example.data.datasource.remote.model.CharacterResponse
import com.example.data.datasource.remote.model.DataResponse
import com.example.data.datasource.remote.model.ImgResponse
import com.example.data.datasource.remote.model.ResultResponse
import com.example.domain.utils.CoroutineResult
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Call
import retrofit2.Response

class CharacterRemoteImplTest {

    private lateinit var marvelApi: MarvelApi
    private lateinit var characterRemoteImpl: CharacterRemoteImpl

    private val mockCallList: Call<DataResponse> = mockk()
    private val mockCallItem: Call<CharacterResponse> = mockk()

    private val characterResponse = CharacterResponse(
        id = ID_HULK,
        name = NAME_HULK,
        description = DESCRIPTION_HULK,
        img = ImgResponse(path = PATH_HULK, ext = EXT)
    )

    @Before
    fun setUp() {
        marvelApi = mockk()
        characterRemoteImpl = CharacterRemoteImpl(marvelApi)
    }

    @After
    fun tearDown() {
        clearAllMocks()
    }

    @Test
    fun `getCharacterList should return Success when API call is successful`() {
        val mockDataResponse =
            DataResponse(data = ResultResponse(listOf(characterResponse).toMutableList()))
        every { marvelApi.getCharacterList() } returns mockCallList
        every { mockCallList.execute() } returns Response.success(mockDataResponse)

        val result = characterRemoteImpl.getCharacterList()

        assertTrue(result is CoroutineResult.Success)
        val characters = (result as CoroutineResult.Success).data
        assertEquals(RESULT_SIZE, characters.size)
        assertEquals(NAME_HULK, characters.first().name)
        assertEquals(FULL_PATH_HULK, characters.first().img)
    }

    @Test
    fun `getCharacterList should return Failure when API call throws exception`() {
        every { marvelApi.getCharacterList() } returns mockCallList
        every { mockCallList.execute() } throws RuntimeException(NETWORK_ERROR)

        val result = characterRemoteImpl.getCharacterList()

        assertTrue(result is CoroutineResult.Failure)
    }

    @Test
    fun `getCharacterList should return Failure when response is not successful`() {
        val errorResponse = Response.error<DataResponse>(
            CODE_500,
            SERVER_ERROR
                .toResponseBody(APPLICATION_JSON.toMediaTypeOrNull())
        )
        every { marvelApi.getCharacterList() } returns mockCallList
        every { mockCallList.execute() } returns errorResponse

        val result = characterRemoteImpl.getCharacterList()

        assertTrue(result is CoroutineResult.Failure)
    }

    @Test
    fun `getCharacter should return Success and properly map data`() {
        every { marvelApi.getCharacter(CODE_101) } returns mockCallItem
        every { mockCallItem.execute() } returns Response.success(characterResponse)

        val result = characterRemoteImpl.getCharacter(CODE_101)

        assertTrue(result is CoroutineResult.Success)
        val character = (result as CoroutineResult.Success).data

        assertEquals(NAME_HULK, character.name)
        assertEquals(DESCRIPTION_HULK, character.description)
        assertEquals(FULL_PATH_HULK, character.img)
        assertEquals(ID_HULK, character.id)
    }

    @Test
    fun `getCharacter should return Failure when API throws exception`() {
        every { marvelApi.getCharacter(CODE_999) } returns mockCallItem
        every { mockCallItem.execute() } throws RuntimeException("Timeout")

        val result = characterRemoteImpl.getCharacter(CODE_999)

        assertTrue(result is CoroutineResult.Failure)
    }

    @Test
    fun `getCharacter should return Failure when response is not successful`() {
        val errorResponse = Response.error<CharacterResponse>(
            CODE_404,
            ERROR_NOT_FOUND.toResponseBody(APPLICATION_JSON.toMediaTypeOrNull())
        )
        every { marvelApi.getCharacter(CODE_999) } returns mockCallItem
        every { mockCallItem.execute() } returns errorResponse

        val result = characterRemoteImpl.getCharacter(CODE_999)

        assertTrue(result is CoroutineResult.Failure)
    }

    companion object {
        private const val ID_HULK = 1011347
        private const val NAME_HULK = "Hulk"
        private const val DESCRIPTION_HULK = "Strongest Avenger"
        private const val PATH_HULK = "http://hulk"
        private const val EXT = "png"
        private const val FULL_PATH_HULK = "http://hulk.png"
        private const val RESULT_SIZE = 1
        private const val NETWORK_ERROR = "Network error"
        private const val CODE_500 = 500
        private const val APPLICATION_JSON = "application/json"
        private const val SERVER_ERROR = "{\"error\":\"Server error\"}"
        private const val ERROR_NOT_FOUND = "{\"error\":\"Not Found\"}"
        private const val CODE_101 = 101
        private const val CODE_404 = 404
        private const val CODE_999 = 999
    }
}
