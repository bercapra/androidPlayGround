package com.example.data.datasource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.datasource.local.entity.CharacterLocalEntity

@Dao
interface MarvelDao {
    @Query("SELECT * FROM marvel_characters")
    fun getDBCharacters(): List<CharacterLocalEntity>

    @Query("SELECT * FROM marvel_characters WHERE id = :characterId")
    fun getCharacter(characterId: Int): List<CharacterLocalEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCharacter(characterEntity: CharacterLocalEntity)
}