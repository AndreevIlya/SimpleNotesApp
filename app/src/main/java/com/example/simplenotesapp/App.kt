package com.example.simplenotesapp

import android.app.Application
import com.example.simplenotesapp.database.NotesDB
import com.example.simplenotesapp.di.global.AppComponent
import com.example.simplenotesapp.di.global.AppContextModule
import com.example.simplenotesapp.di.global.DaggerAppComponent
import com.example.simplenotesapp.di.global.RoomModule
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
            .build()
        appComponent.inject(this)
    }
}