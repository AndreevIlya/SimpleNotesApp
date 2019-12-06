package com.example.simplenotesapp.domain

import androidx.lifecycle.LiveData

interface Repository {
    suspend fun addNote(note: String)
    suspend fun updateNote(id: Long,note: String)
    suspend fun deleteNote(id: Long)
    fun getNotes(): LiveData<List<NoteEntity>>

}