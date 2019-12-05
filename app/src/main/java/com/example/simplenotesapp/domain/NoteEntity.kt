package com.example.simplenotesapp.domain

import com.example.simplenotesapp.data.database.room.Note

data class NoteEntity(
    val id: Long,
    val note: String
){
    fun convertToRoom(): Note = Note(id,note)

    companion object{
        fun convertToRoom(text: String) =
            Note(0, text)

        fun convertFromRoom(note: Note) = NoteEntity(note.id,note.note)
    }
}