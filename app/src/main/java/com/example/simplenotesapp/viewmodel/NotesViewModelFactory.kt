package com.example.simplenotesapp.viewmodel

import android.app.Activity
import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.simplenotesapp.database.NotesDao
import com.example.simplenotesapp.ui.MainActivity

class NotesViewModelFactory(
    private val source: NotesDao,
    private val context: MainActivity
): ViewModelProvider.Factory {


    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NotesViewModel::class.java)) {
            return NotesViewModel(source, context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}