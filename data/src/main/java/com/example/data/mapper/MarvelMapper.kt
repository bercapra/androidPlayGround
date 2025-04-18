package com.example.data.mapper

import com.example.data.datasource.local.entity.CharacterLocalEntity
import com.example.data.datasource.remote.model.CharacterResponse
import com.example.data.datasource.remote.model.DataResponse
import com.example.domain.entity.MarvelCharacter
import com.example.domain.utils.Constants.DOT

fun DataResponse.mapToLocalCharacterList(): List<MarvelCharacter> = data.characters.map { it.mapToLocalCharacter() }

fun CharacterResponse.mapToLocalCharacter() =
    MarvelCharacter(
        id = id,
        name = name,
        description = description,
        img = "${img.path}$DOT${img.ext}"
    )

fun CharacterLocalEntity.mapToLocalCharacter() =
    MarvelCharacter(
        id = id,
        name = name,
        description = description,
        img = img
    )

fun MarvelCharacter.mapToDataBaseCharacter() =
    CharacterLocalEntity(
        id = id,
        name = name,
        description = description,
        img = img
    )

fun List<CharacterLocalEntity>.mapToCharacterList() = this.map { it.mapToLocalCharacter() }
