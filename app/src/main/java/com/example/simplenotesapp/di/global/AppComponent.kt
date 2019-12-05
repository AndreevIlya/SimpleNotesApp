package com.example.simplenotesapp.di.global

import android.app.Application
import com.example.simplenotesapp.domain.Repository
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [AppContextModule::class,
        RoomModule::class,
        RepositoryModule::class]
)
@Singleton
interface AppComponent {
    fun inject(app: Application)
    fun provideRepository(): Repository
}