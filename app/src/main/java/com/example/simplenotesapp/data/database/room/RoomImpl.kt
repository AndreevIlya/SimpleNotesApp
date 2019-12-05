package com.example.simplenotesapp.data.database.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.simplenotesapp.data.database.DBProvider
import com.example.simplenotesapp.domain.NoteEntity


class RoomImpl(val database: NotesDB) : DBProvider<Note, NoteEntity> {
    override fun insert(data: Note) {
        database.getNotesDao().addNote(data)
    }

    override fun update(data: Note) {
        database.runInTransaction { database.getNotesDao().updateNote(data) }
    }

    override fun delete(data: Note) {
        database.runInTransaction { database.getNotesDao().deleteNote(data) }
    }

    override fun getNotes(): LiveData<List<NoteEntity>> = Transformations.map(
        database.getNotesDao().getNotes()
    ) { list ->
        list.map{NoteEntity.convertFromRoom(it)}
    }
}