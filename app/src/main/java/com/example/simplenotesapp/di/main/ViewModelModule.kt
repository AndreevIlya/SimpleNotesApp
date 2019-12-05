package com.example.simplenotesapp.di.main

import androidx.lifecycle.ViewModelProviders
import com.example.simplenotesapp.domain.Repository
import com.example.simplenotesapp.ui.MainActivity
import com.example.simplenotesapp.viewmodel.NotesViewModel
import com.example.simplenotesapp.viewmodel.NotesViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ViewModelModule(private val activity: MainActivity) {
    @Provides
    @MainScope
    fun provideNotesViewModel(repository: Repository):NotesViewModel{
        val factory = NotesViewModelFactory(repository,activity)
        return ViewModelProviders.of(activity,factory).get(NotesViewModel::class.java)
    }
}