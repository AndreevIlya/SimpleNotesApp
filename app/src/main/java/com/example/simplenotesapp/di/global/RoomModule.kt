package com.example.simplenotesapp.di.global

import android.app.Application
import androidx.room.Room
import com.example.simplenotesapp.database.NotesDB
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {
    @Provides
    @Singleton
    fun provideRoom(context: Application): NotesDB{
        return Room.databaseBuilder(context,NotesDB::class.java,"notes")
            .fallbackToDestructiveMigration()
            .build()
    }
}