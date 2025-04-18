package com.example.data.datasource.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImgResponse(
    @SerialName("path") val path: String,
    @SerialName("extension") val ext: String
) : java.io.Serializable