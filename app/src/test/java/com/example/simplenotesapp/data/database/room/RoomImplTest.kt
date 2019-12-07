package com.example.simplenotesapp.data.database.room

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RoomImplTest{
    val database = mock(NotesDB::class.java)
    val room = RoomImpl(database)

    @Test
    fun testAddAndGet(){
        val note = Note(1L,"note")

    }

}