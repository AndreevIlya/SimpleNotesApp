package com.example.simplenotesapp.ui

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.simplenotesapp.App
import com.example.simplenotesapp.R
import com.example.simplenotesapp.databinding.ActivityMainBinding
import com.example.simplenotesapp.di.main.AdapterModule
import com.example.simplenotesapp.di.main.DaggerMainComponent
import com.example.simplenotesapp.di.main.ViewModelModule
import com.example.simplenotesapp.viewmodel.NotesViewModel
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var viewModel: NotesViewModel
    @Inject
    lateinit var adapter: NotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDependencies()
        initDataBinding()
        initListeners()
        initObservers()
    }

    private fun initListeners() {
        binding.addButton.setOnClickListener {
            viewModel.saveNote(binding.addNote.text.toString())
            binding.addNote.setText("", TextView.BufferType.EDITABLE)
            handleFocus()
        }
    }

    private fun initObservers() {
        viewModel.notes.observe(this, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })
        viewModel.showError.observe(this, Observer {
            if (it) {
                Snackbar.make(
                    this.findViewById(android.R.id.content),
                    R.string.blank_input_error,
                    Snackbar.LENGTH_SHORT
                )
                    .show()
                viewModel.hideError()
            }
        })
        adapter.deleteRequest.observe(this, Observer {
            viewModel.deleteNote(it)
        })
        adapter.updateRequest.observe(this, Observer {
            viewModel.updateNote(it.id, it.note)
            handleFocus()
        })
    }

    private fun initDataBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.notesViewModel = viewModel
        binding.notes.adapter = adapter
    }

    private fun initDependencies() {
        val appComp = (application as App).appComponent
        DaggerMainComponent.builder()
            .appComponent(appComp)
            .viewModelModule(ViewModelModule(this))
            .adapterModule(AdapterModule())
            .build()
            .inject(this)
    }

    private fun handleFocus() {
        binding.mainLayout.requestFocus()
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.addNote.windowToken, 0)
    }
}
