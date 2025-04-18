package com.example.data.datasource.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "marvel_characters")
class CharacterLocalEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val description: String,
    val img: String
)