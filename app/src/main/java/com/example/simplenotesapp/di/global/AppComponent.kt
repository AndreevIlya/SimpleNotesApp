package com.example.simplenotesapp.di.global

import android.app.Application
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppContextModule::class,RoomModule::class])
@Singleton
interface AppComponent {
    fun inject(app: Application)
}