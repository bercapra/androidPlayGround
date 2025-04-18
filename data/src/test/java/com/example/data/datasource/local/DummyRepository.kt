package com.example.data.datasource.local

import com.example.data.datasource.local.entity.CharacterLocalEntity

class DummyRepository(private val dao: MarvelDao) {

    fun getAllCharacters(): List<CharacterLocalEntity> {
        return dao.getDBCharacters()
    }

    fun getCharacterById(id: Int): CharacterLocalEntity? {
        return dao.getCharacter(id).firstOrNull()
    }

    fun saveCharacter(character: CharacterLocalEntity) {
        dao.insertCharacter(character)
    }
}
