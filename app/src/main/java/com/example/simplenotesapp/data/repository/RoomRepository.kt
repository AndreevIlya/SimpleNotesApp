package com.example.simplenotesapp.data.repository

import androidx.lifecycle.LiveData
import com.example.simplenotesapp.data.database.DBProvider
import com.example.simplenotesapp.data.database.room.Note
import com.example.simplenotesapp.domain.NoteEntity
import com.example.simplenotesapp.domain.Repository

class RoomRepository(
    private val provider: DBProvider<Note, NoteEntity>
) : Repository {
    override suspend fun addNote(note: String) {
        provider.insert(NoteEntity.convertToRoom(note))
    }

    override suspend fun updateNote(id: Long, note: String) {
        provider.update(NoteEntity(id,note).convertToRoom())
    }

    override suspend fun deleteNote(id: Long) {
        provider.delete(NoteEntity(id,"").convertToRoom())
    }

    override fun getNotes(): LiveData<List<NoteEntity>> = provider.getNotes()
}