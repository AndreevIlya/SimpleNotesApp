package com.example.simplenotesapp.domain

import androidx.lifecycle.LiveData

interface Repository {
    fun addNote(note: String)
    fun updateNote(id: Long,note: String)
    fun deleteNote(id: Long)
    fun getNotes(): LiveData<List<NoteEntity>>

}