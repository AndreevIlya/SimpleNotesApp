package com.example.simplenotesapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.simplenotesapp.App
import com.example.simplenotesapp.R
import com.example.simplenotesapp.databinding.ActivityMainBinding
import com.example.simplenotesapp.di.main.DaggerMainComponent
import com.example.simplenotesapp.di.main.ViewModelModule
import com.example.simplenotesapp.viewmodel.NotesViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    @Inject
    lateinit var viewModel: NotesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDependencies()
        initDataBinding()
/*        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }*/

    }

    private fun initDataBinding() {
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.notesViewModel = viewModel
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
