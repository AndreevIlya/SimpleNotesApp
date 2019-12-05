package com.example.simplenotesapp.di.main

import com.example.simplenotesapp.di.global.AppComponent
import com.example.simplenotesapp.ui.MainActivity
import dagger.Component
import javax.inject.Inject

@Component(modules = [ViewModelModule::class]
    ,dependencies = [AppComponent::class])
@MainScope
interface MainComponent {

    fun inject(activity: MainActivity)
}