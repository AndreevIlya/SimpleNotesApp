package com.example.simplenotesapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.example.simplenotesapp.data.database.room.Note
import com.example.simplenotesapp.data.database.room.NotesDB
import com.example.simplenotesapp.data.database.room.NotesDao
import com.example.simplenotesapp.ui.MainActivity
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

@RunWith(AndroidJUnit4::class)
class RoomTest {
    private lateinit var database: NotesDB
    private lateinit var dao: NotesDao
    private lateinit var note: Note

    @get:Rule
    var rule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, NotesDB::class.java)
            .allowMainThreadQueries()
            .build()
        dao = database.getNotesDao()
        note = Note(0, "note")
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        database.close()
    }

    @Test
    @Throws(Exception::class)
    fun addAndGetNote() {
        dao.addNote(note)
        val noteList = dao.getNotes().blockingObserve()
        val noteResult = noteList?.get(0)
        assertEquals(note.note, noteResult?.note)
    }

    private fun <T> LiveData<T>.blockingObserve(): T? {
        var value: T? = null
        val latch = CountDownLatch(1)

        val observer = Observer<T> { t ->
            value = t
            latch.countDown()
        }
        observe(rule.activity,observer)


        latch.await(2, TimeUnit.SECONDS)
        return value
    }
}