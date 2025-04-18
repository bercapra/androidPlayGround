package com.example.data.datasource.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DataResponse(
    @SerialName("data") val data: ResultResponse
) : java.io.Serializable