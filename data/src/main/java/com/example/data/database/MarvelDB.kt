package com.example.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.database.entity.CharacterEntity

@Database(entities = [CharacterEntity::class], version = 1)
abstract class MarvelDB : RoomDatabase() {
    abstract fun marvelDao(): MarvelDao
}