package com.example.simplenotesapp

import android.app.Application
import com.example.simplenotesapp.data.database.room.NotesDB
import com.example.simplenotesapp.di.global.*
import javax.inject.Inject

class App: Application() {

    lateinit var appComponent: AppComponent

    @Inject
    lateinit var notesDB: NotesDB

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .appContextModule(AppContextModule(this))
            .roomModule(RoomModule())
            .repositoryModule(RepositoryModule())
            .build()
        appComponent.inject(this)
    }
}