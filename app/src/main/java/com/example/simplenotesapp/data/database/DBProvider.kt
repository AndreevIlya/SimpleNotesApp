package com.example.simplenotesapp.data.database

import androidx.lifecycle.LiveData

interface DBProvider<T,F> {
    fun insert(data: T)

    fun update(data: T)

    fun delete(data: T)

    fun getNotes(): LiveData<List<F>>
}