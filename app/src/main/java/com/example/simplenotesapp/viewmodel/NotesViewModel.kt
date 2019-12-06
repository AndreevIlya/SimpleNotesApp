package com.example.simplenotesapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.simplenotesapp.domain.Repository
import kotlinx.coroutines.*

class NotesViewModel(
    val repository: Repository
) : ViewModel() {
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)
    val notes = repository.getNotes()

    private val error = MutableLiveData<Boolean>()
    val showError: LiveData<Boolean>
        get() = error

    fun hideError() {
        error.value = false
    }

    fun saveNote(text: String) {
        if (!text.isBlank()) {
            uiScope.launch {
                withContext(Dispatchers.IO) {
                    repository.addNote(text)
                }
            }
        }else{
            error.value = true
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}