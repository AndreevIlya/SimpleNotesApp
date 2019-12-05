package com.example.simplenotesapp.data.database.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Note::class],version = 1,exportSchema = false)
abstract class NotesDB: RoomDatabase() {
    abstract fun getNotesDao(): NotesDao
}