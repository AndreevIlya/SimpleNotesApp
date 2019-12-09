package com.example.simplenotesapp.di.main

import com.example.simplenotesapp.ui.NotesAdapter
import dagger.Module
import dagger.Provides

@Module
class AdapterModule {
    @Provides
    @MainScope
    fun provideAdapter() = NotesAdapter()
}