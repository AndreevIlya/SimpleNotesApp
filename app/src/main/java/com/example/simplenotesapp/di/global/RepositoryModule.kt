package com.example.simplenotesapp.di.global

import com.example.simplenotesapp.data.database.DBProvider
import com.example.simplenotesapp.data.database.room.Note
import com.example.simplenotesapp.data.repository.RoomRepository
import com.example.simplenotesapp.domain.NoteEntity
import com.example.simplenotesapp.domain.Repository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideRoomRepository(provider: DBProvider<Note, NoteEntity>): Repository {
        return RoomRepository(provider)
    }
}