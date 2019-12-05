package com.example.simplenotesapp.di.global

import android.app.Application
import androidx.room.Room
import com.example.simplenotesapp.data.database.DBProvider
import com.example.simplenotesapp.data.database.room.Note
import com.example.simplenotesapp.data.database.room.NotesDB
import com.example.simplenotesapp.data.database.room.RoomImpl
import com.example.simplenotesapp.domain.NoteEntity
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {
    @Provides
    @Singleton
    fun provideNotesDatabase(context: Application): DBProvider<Note,NoteEntity> {
        val database =  Room.databaseBuilder(context,
            NotesDB::class.java,"notes")
            .fallbackToDestructiveMigration()
            .build()
        return RoomImpl(database)
    }
}