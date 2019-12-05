package com.example.simplenotesapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simplenotesapp.domain.Repository
import com.example.simplenotesapp.ui.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class NotesViewModel(
    val repository: Repository,
    val context: MainActivity
) : ViewModel() {
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    private val note = MutableLiveData<String>()

    fun saveNote(text: String){
        note.value = text
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}