package com.example.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.datasource.local.entity.CharacterLocalEntity

@Database(entities = [CharacterLocalEntity::class], version = 1)
abstract class MarvelDB : RoomDatabase() {
    abstract fun marvelDao(): MarvelDao
}