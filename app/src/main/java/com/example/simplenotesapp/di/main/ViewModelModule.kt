package com.example.simplenotesapp.di.main

import androidx.lifecycle.ViewModelProviders
import com.example.simplenotesapp.database.NotesDao
import com.example.simplenotesapp.ui.MainActivity
import com.example.simplenotesapp.viewmodel.NotesViewModel
import com.example.simplenotesapp.viewmodel.NotesViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ViewModelModule(private val activity: MainActivity) {
    @Provides
    @MainScope
    fun provideNotesViewModel(dataBase: NotesDao):NotesViewModel{
        val factory = NotesViewModelFactory(dataBase,activity)
        return ViewModelProviders.of(activity,factory).get(NotesViewModel::class.java)
    }
}