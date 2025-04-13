package com.example.data.mapper

import com.example.data.service.model.CharacterResponse
import com.example.data.service.model.DataResponse
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
/*
fun CharacterEntity.mapToLocalCharacter() =
    MarvelCharacter(
        id = id,
        name = name,
        description = description,
        img = img
    )*/
/*
fun MarvelCharacter.mapToDataBaseCharacter() =
    CharacterEntity(
        id = id,
        name = name,
        description = description,
        img = img
    )*/

//fun List<CharacterEntity>.mapToCharacterList() = this.map { it.mapToLocalCharacter() }