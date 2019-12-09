package com.example.simplenotesapp.di.main

import com.example.simplenotesapp.di.global.AppComponent
import com.example.simplenotesapp.ui.MainActivity
import dagger.Component

@Component(modules = [ViewModelModule::class,AdapterModule::class]
    ,dependencies = [AppComponent::class])
@MainScope
interface MainComponent {

    fun inject(activity: MainActivity)
}