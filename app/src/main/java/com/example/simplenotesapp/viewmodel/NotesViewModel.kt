package com.example.simplenotesapp.viewmodel

import android.app.Activity
import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.simplenotesapp.database.NotesDao
import com.example.simplenotesapp.ui.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class NotesViewModel(
    val database: NotesDao,
    val context: MainActivity
): ViewModel() {
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)




    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}