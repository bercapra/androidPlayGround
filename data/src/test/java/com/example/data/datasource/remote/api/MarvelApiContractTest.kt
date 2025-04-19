package com.example.data.datasource.remote.api

import com.example.data.datasource.remote.model.CharacterResponse
import com.example.data.datasource.remote.model.DataResponse
import com.example.data.datasource.remote.model.ImgResponse
import com.example.data.datasource.remote.model.ResultResponse
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import retrofit2.Call
import retrofit2.Response

class MarvelApiContractTest {

    private val api: MarvelApi = mockk()
    private val mockListCall: Call<DataResponse> = mockk()
    private val mockSingleCall: Call<CharacterResponse> = mockk()

    private lateinit var ironMan: CharacterResponse
    private lateinit var thor: CharacterResponse

    @Before
    fun setUp() {
        ironMan = CharacterResponse(
            id = ID_IRON,
            name = NAME_IRON,
            description = DESCRIPTION_IRON,
            img = ImgResponse(path = PATH_IRON, ext = EXT)
        )

        thor = CharacterResponse(
            id = ID_THOR,
            name = NAME_THOR,
            description = DESCRIPTION_THOR,
            img = ImgResponse(path = PATH_THOR, ext = EXT)
        )
    }

    @Test
    fun `should return character list from API`() {
        val dataResponse =
            DataResponse(data = ResultResponse(listOf(ironMan, thor).toMutableList()))

        every { api.getCharacterList() } returns mockListCall
        every { mockListCall.execute() } returns Response.success(dataResponse)

        val response = api.getCharacterList().execute()

        assertNotNull(response.body())
        assertEquals(TWO, response.body()?.data?.characters?.size)
        assertEquals(NAME_IRON, response.body()?.data?.characters?.first()?.name)

        verify(exactly = 1) { api.getCharacterList() }
    }

    @Test
    fun `should return character by ID from API`() {
        every { api.getCharacter(ID_THOR) } returns mockSingleCall
        every { mockSingleCall.execute() } returns Response.success(thor)

        val response = api.getCharacter(ID_THOR).execute()

        assertNotNull(response.body())
        assertEquals(ID_THOR, response.body()?.id)
        assertEquals(NAME_THOR, response.body()?.name)

        verify(exactly = 1) { api.getCharacter(ID_THOR) }
    }

    @Test
    fun `should return error response when character is not found`() {
        val errorBody = ERROR_NOT_FOUND
            .toResponseBody(APPLICATION_JSON.toMediaTypeOrNull())
        val errorResponse = Response.error<CharacterResponse>(CODE_404, errorBody)

        every { api.getCharacter(ID_MISSING) } returns mockSingleCall
        every { mockSingleCall.execute() } returns errorResponse

        val response = api.getCharacter(ID_MISSING).execute()

        assertEquals(FALSE, response.isSuccessful)
        assertEquals(CODE_404, response.code())

        verify(exactly = 1) { api.getCharacter(ID_MISSING) }
    }

    companion object {
        private const val ID_IRON = 1011348
        private const val NAME_IRON = "Iron Man"
        private const val DESCRIPTION_IRON = "Genius, billionaire, playboy, philanthropist"
        private const val PATH_IRON = "http://ironman"
        private const val ID_THOR = 1011349
        private const val NAME_THOR = "Thor"
        private const val DESCRIPTION_THOR = "God of Thunder"
        private const val PATH_THOR = "http://thor"
        private const val EXT = "jpg"
        private const val ID_MISSING = 999
        private const val APPLICATION_JSON = "application/json"
        private const val ERROR_NOT_FOUND = "{\"error\":\"Character not found\"}"
        private const val FALSE = false
        private const val CODE_404 = 404
        private const val TWO = 2
    }
}
