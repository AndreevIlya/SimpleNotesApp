package com.example.simplenotesapp.di.global

import android.app.Application
import androidx.room.Room
import com.example.simplenotesapp.database.NotesDB
import com.example.simplenotesapp.database.NotesDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {
    @Provides
    @Singleton
    fun provideNotesDatabase(context: Application): NotesDB{
        return Room.databaseBuilder(context,NotesDB::class.java,"notes")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNotesDao(database: NotesDB): NotesDao{
        return database.getNotesDao()
    }
}