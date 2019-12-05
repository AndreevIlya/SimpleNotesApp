package com.example.simplenotesapp.ui

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.simplenotesapp.App
import com.example.simplenotesapp.R
import com.example.simplenotesapp.di.global.AppComponent
import com.example.simplenotesapp.di.global.DaggerAppComponent
import com.example.simplenotesapp.di.main.DaggerMainComponent
import com.example.simplenotesapp.di.main.MainComponent
import com.example.simplenotesapp.di.main.ViewModelModule
import com.example.simplenotesapp.viewmodel.NotesViewModel

import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: NotesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        initDependencies()
/*        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }*/

    }

    private fun initDependencies() {
        val appComp = (application as App).appComponent
        DaggerMainComponent.builder()
            .appComponent(appComp)
            .viewModelModule(ViewModelModule(this))
            .build()
            .inject(this)
    }
}
