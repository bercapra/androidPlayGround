package com.example.data.datasource.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResultResponse(
    @SerialName("results") val characters: MutableList<CharacterResponse>
) : java.io.Serializable